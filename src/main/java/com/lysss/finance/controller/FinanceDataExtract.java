package com.lysss.finance.controller;

import com.lysss.finance.entity.FinanceData;
import com.lysss.finance.schdule.DataExtractSchedule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FinanceDataExtract {

    @Resource
    private DataExtractSchedule dataExtractSchedule;

    @GetMapping("pull")
    public List<FinanceData> pullData(){
        return  dataExtractSchedule.pullFundData();
    }
}
