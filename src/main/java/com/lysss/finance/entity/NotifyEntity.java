package com.lysss.finance.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.lysss.finance.common.AnalyseType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class NotifyEntity {
    @Excel(name = "类型")
    private AnalyseType analyseType;
    private String code;
    @Excel(name = "指数名称")
    private String name;
    @Excel(name = "开盘价")
    private double open;
    @Excel(name = "收盘价")
    private double close;
    @Excel(name = "涨幅")
    private double rate;
    @Excel(name = "日期")
    private LocalDate data;

    public static NotifyEntity buildEntity(FinanceData financeData) {
        final NotifyEntity notifyEntity = new NotifyEntity();
        // TODO 类型不一样能不能进行copy
        BeanUtils.copyProperties(financeData, notifyEntity);
        notifyEntity.setClose(financeData.getValue());
        notifyEntity.setData(financeData.getDate());
        return notifyEntity;
    }

}
