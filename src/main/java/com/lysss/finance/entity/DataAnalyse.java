package com.lysss.finance.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.lysss.finance.common.AnalyseType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "finance_data_analyse")
@Entity
@Data
public class DataAnalyse {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ApiModelProperty("指数名称")
    @JSONField(name = "f14")
    private String name;
    @ApiModelProperty("指数代码")
    @JSONField(name = "f12")
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("数据时间")
    private LocalDateTime time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("数据日期")
    private LocalDate date;
    @ApiModelProperty("指数值")
    @JSONField(name = "f2")
    @Column(columnDefinition = "DOUBLE")
    private double value;
    @Column(columnDefinition = "DOUBLE")
    @JSONField(name = "f15")
    @ApiModelProperty("最高")
    private double high;
    @Column(columnDefinition = "DOUBLE")
    @JSONField(name = "f16")
    @ApiModelProperty("最低")
    private double low;
    @Column(columnDefinition = "DOUBLE")
    @ApiModelProperty("涨幅")
    @JSONField(name = "f3")
    private double rate;
    @Column(columnDefinition = "DOUBLE")
    @JSONField(name = "f4")
    @ApiModelProperty("涨跌额")
    private double charge;
    @JSONField(name = "f17")
    @Column(columnDefinition = "DOUBLE")
    @ApiModelProperty("开盘价")
    private double open;
    @JSONField(name = "f18")
    @Column(columnDefinition = "DOUBLE")
    @ApiModelProperty("昨夜收盘价")
    private double prevClose;
    @ApiModelProperty("数据获取时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataTime;
    @Transient
    @ApiModelProperty("小数点位数")
    @JSONField(name = "f1")
    private int precision;
    @ApiModelProperty("数据分析类型")
    @Enumerated(EnumType.STRING)
    private AnalyseType type;
}
