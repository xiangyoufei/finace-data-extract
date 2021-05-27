package com.lysss.finance.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lysss.finance.common.NotifyEvent;
import com.lysss.finance.dao.FinanceDataRepository;
import com.lysss.finance.dao.FinanceIndicatorRepository;
import com.lysss.finance.entity.FinanceData;
import com.lysss.finance.entity.NotifyEntity;
import com.lysss.finance.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataSourceServiceImpl implements DataSourceService {


    @Value("${schedule.fund.url}")
    private String fundUrl;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private FinanceDataRepository financeDataRepository;

    @Resource
    private FinanceIndicatorRepository financeIndicatorRepository;

    @Resource
    private ApplicationEventPublisher eventPublisher;


    @Override
    public void pullAndSaveFundData() {
        final List<FinanceData> financeData = this.pullFundData();
        financeDataRepository.saveAll(financeData);
    }

    @Override
    public void notifyEveryDayBeforeClose() {
        final List<FinanceData> financeData = this.pullFundData();
        final List<NotifyEntity> notifyEntities = financeData.stream()
                .map(NotifyEntity::buildEntity)
                .collect(Collectors.toList());
        log.info("发送通知事件");
        eventPublisher.publishEvent(new NotifyEvent<>("all",notifyEntities));
    }


    @Override
    public List<FinanceData> pullFundData() {
        final List<String> fullCode = financeIndicatorRepository.getAllFullCode();
        final String allCode = fullCode.stream().collect(Collectors.joining(","));
        String url = fundUrl.replace("{secids}", allCode);
        final LocalDateTime now = LocalDateTime.now();
        final LocalDate date = now.toLocalDate();
        final long time = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        final String endpoint = url.replaceAll("\\{tm\\}", time + "");
        log.info("send httpRequest, url:{}", endpoint);
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(endpoint, String.class);
        List<FinanceData> financeData = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            final JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            final String string = jsonObject.getJSONObject("data").getString("diff");
            financeData = JSONArray.parseArray(string, FinanceData.class).stream()
                    .peek(FinanceData::dealPrecision)
                    .peek(item -> item.setDataTime(now))
                    .peek(item -> item.setDate(date))
                    .peek(item -> item.setTime(now))
                    .collect(Collectors.toList());
        }
        return Optional.ofNullable(financeData).orElse(Collections.EMPTY_LIST);
    }

}
