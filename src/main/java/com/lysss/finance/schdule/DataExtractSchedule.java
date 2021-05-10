package com.lysss.finance.schdule;

import com.lysss.finance.dao.FinanceDataRepository;
import com.lysss.finance.entity.FinanceData;
import com.lysss.finance.service.CommonService;
import com.lysss.finance.service.DataSourceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataExtractSchedule {

    @Value("${schedule.fund.url}")
    private String fundUrl;

    @Resource
    private FinanceDataRepository financeDataRepository;

    @Resource
    private DataSourceService dataSourceService;


    @Schedules({
//            @Scheduled(cron = "${schedule.fund.cron}"),
            @Scheduled(cron = "${schedule.fund.cron}")
    })
    public void pullDataAndInsert() {
        if (CommonService.isTradingDay(true)) {
            final List<FinanceData> financeData = dataSourceService.pullFundData();
            financeDataRepository.saveAll(financeData);
        }
    }

}
