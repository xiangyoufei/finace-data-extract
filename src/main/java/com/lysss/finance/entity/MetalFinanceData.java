package com.lysss.finance.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.sun.org.glassfish.gmbal.DescriptorFields;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MetalFinanceData {

    @DescriptorFields("指数名称")
    @JSONField(name = "metalname")
    private String name;
    @DescriptorFields("指数代码")
    @JSONField(name = "prodcode")
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DescriptorFields("数据时间")
    private LocalDateTime time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DescriptorFields("数据日期")
    private LocalDate date;
    @DescriptorFields("指数值")
    @JSONField(name = "middleprice")
    @Column(columnDefinition = "DOUBLE")
    private double value;
    @Column(columnDefinition = "DOUBLE")
    @JSONField(name = "topmiddleprice")
    @DescriptorFields("最高")
    private double high;
    @Column(columnDefinition = "DOUBLE")
    @JSONField(name = "lowmiddleprice")
    @DescriptorFields("最低")
    private double low;
    @Column(columnDefinition = "DOUBLE")
    @DescriptorFields("涨幅")
    @JSONField(name = "openprice_dr")
    private double rate;
    @Column(columnDefinition = "DOUBLE")
    @JSONField(name = "openprice_dv")
    @DescriptorFields("涨跌额")
    private double charge;
    @JSONField(name = "openprice_yr")
    @Column(columnDefinition = "DOUBLE")
    @DescriptorFields("当年涨跌幅")
    private double rateYOY;

    // todo 一下未知含义
    @JSONField(name = "upordown")
    @DescriptorFields("当日涨跌情况， 1 - 跌了  2-涨了")
    private int upordown;
    @JSONField(name = "updown_y")
    private double updown_y;
    @JSONField(name = "updown_d")
    private double updown_d;

}
