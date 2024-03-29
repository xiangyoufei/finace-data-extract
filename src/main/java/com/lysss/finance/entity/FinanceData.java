package com.lysss.finance.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Entity
@Data
@Table(name = "finance_data")
public class FinanceData {

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

    public void dealPrecision() {
        final Field[] declaredFields = FinanceData.class.getDeclaredFields();
        if (precision == 0) {
            return;
        }
        Stream.of(declaredFields)
                .filter(item -> item.getType() == double.class)
                .forEach(item -> {
                    item.setAccessible(true);
                    try {
                        final double value = (double) item.get(this);
//                        item.set(this, value.divide(double.valueOf(10 * precision)));
                        item.set(this, value / (Math.pow(10, precision)));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}
