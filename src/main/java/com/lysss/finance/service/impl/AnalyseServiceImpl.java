package com.lysss.finance.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lysss.finance.common.AnalyseType;
import com.lysss.finance.dao.DataAnalyseRepository;
import com.lysss.finance.dao.FinanceDataRepository;
import com.lysss.finance.entity.DataAnalyse;
import com.lysss.finance.entity.FinanceData;
import com.lysss.finance.service.AnalyseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnalyseServiceImpl implements AnalyseService {

    @Resource
    private FinanceDataRepository financeDataRepository;

    @Resource
    private DataAnalyseRepository dataAnalyseRepository;

    @Override
    public void analyseByDay() {
        final List<FinanceData> data = financeDataRepository.findALLIndicatorCloseValue();
        final List<DataAnalyse> analyseData = JSONArray.parseArray(JSON.toJSONString(data), DataAnalyse.class);
        analyseData.forEach(item -> item.setType(AnalyseType.DAILY));
        dataAnalyseRepository.saveAll(analyseData);
    }

    @Override
    public void analyseMetalData() {
        final List<FinanceData> data = financeDataRepository.findALLIndicatorCloseValue();
        final List<DataAnalyse> analyseData = JSONArray.parseArray(JSON.toJSONString(data), DataAnalyse.class);
        analyseData.forEach(item -> item.setType(AnalyseType.DAILY));
        dataAnalyseRepository.saveAll(analyseData);
    }


}
