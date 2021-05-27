package com.lysss.finance.common;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSONObject;
import com.lysss.finance.entity.NotifyEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Async
@Slf4j
public class EmilService {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${spring.mail.sender}")
    private String sender;

/*    @Value("#{'${spring.mail.receiver}'.split(',')}")
    private List<String> receive;*/

    @Value("${spring.mail.receiver}")
    private String[] receive;

    @EventListener(condition = "#event.source == 'single'")
    public void sendTextMail(NotifyEvent<NotifyEntity> event) {
        log.info("收到事件通知");
        NotifyEntity notifyEntity = event.getData();
        SimpleMailMessage msg = new SimpleMailMessage(buildBaseInfo());
        msg.setSubject("每日金融数据通报");
        msg.setText(JSONObject.toJSONString(notifyEntity));
        this.mailSender.send(msg);
        log.info("邮件发送成功");
    }

    //构建复杂邮件信息类
//    @EventListener(condition = "#event.source != 'single'")
    public void sendMimeMail(NotifyEvent<List<NotifyEntity>> event) {
        log.info("监听到通知事件");
        final List<NotifyEntity> notifyEntities = event.getData();
        final Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), NotifyEntity.class, notifyEntities);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            helper.setSubject("今日财经数据");
            helper.setTo(receive);
            helper.setFrom(sender);
            helper.setText("每日采集数据，请及时查看，谨慎下单操作！");
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            final InputStreamResource inputStreamResource = new InputStreamResource(bis);
//            helper.addInline("contentId", inputStreamResource);
            helper.addAttachment("finanace Data.xlsx", new ByteArrayResource(IOUtils.toByteArray(inputStreamResource.getInputStream())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("开始发送邮件");
        mailSender.send(message);
        log.info("邮件发送成功");
    }


    private SimpleMailMessage buildBaseInfo() {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(String.join(",", receive));
        simpleMailMessage.setFrom(sender);
        return simpleMailMessage;
    }

    @EventListener(condition = "#event.source != 'single'")
    public void sendTemplateMail(NotifyEvent<List<NotifyEntity>> event) {
        log.info("监听到通知事件");
        final List<NotifyEntity> notifyEntities = event.getData();
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("今日财经数据，日期:"+ LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            helper.setTo(receive);
            helper.setFrom(sender);
            //使用模板thymeleaf 定义模板数据
            Context context = new Context();
            context.setVariable("title", "今日财经数据");
            context.setVariable("datas", notifyEntities);
            //获取thymeleaf的html模板
            String emailContent = templateEngine.process("email", context); //指定模板路径
            helper.setText(emailContent, true);
//            发送邮件
            mailSender.send(mimeMessage);
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("模板邮件发送失败->message:{}", e.getMessage());
            throw new RuntimeException("邮件发送失败");
        }
    }
}
