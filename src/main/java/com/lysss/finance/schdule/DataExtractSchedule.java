package com.lysss.finance.schdule;

import com.lysss.finance.service.AnalyseService;
import com.lysss.finance.service.DataSourceService;
import com.lysss.finance.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class DataExtractSchedule {

    @Value("${resources.fund.url}")
    private String fundUrl;

    @Resource(name = "fundData")
    private DataSourceService dataSourceService;

    @Resource(name = "metalData")
    private DataSourceService metalDataService;

    @Resource
    private AnalyseService analyseService;

    /**
     * 获取金融指数并且入库
     */
    @Scheduled(cron = "${schedule.fund.cron}")
    public void pullDataAndInsert() {
        if (DataUtil.isTradingDay(true)) {
            dataSourceService.pullAndSaveFundData();
        }
    }
    /**
     * 获取金融指数并且入库
     */
    @Scheduled(cron = "${schedule.metal.cron}")
    public void pullMetalDataAndInsert() {
        if (DataUtil.isTradingDay(true)) {
            dataSourceService.pullAndSaveFundData();
        }
    }

    @Scheduled(cron = "${schedule.analyse.cron.daily}")
    public void analyseDaily() {
        log.info("执行每日分析任务");
        if (DataUtil.isTradingDay(true)) {
            analyseService.analyseByDay();
        }
    }

    @Scheduled(cron = "${schedule.notify.cron}")
    public void notifyEveryDayBeforeClose() {
        log.info("执行每日数据提醒的任务");
        if (DataUtil.isTradingDay(true)) {
            dataSourceService.notifyEveryDayBeforeClose();
        }
    }

}
