package com.lysss.finance.service;

import com.lysss.finance.entity.FinanceData;

import java.util.List;

public interface DataSourceService {
    void pullAndSaveFundData();

    List<FinanceData> pullFundData();

    void notifyEveryDayBeforeClose();
}
