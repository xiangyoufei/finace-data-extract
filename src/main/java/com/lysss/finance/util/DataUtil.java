package com.lysss.finance.util;

import com.lysss.finance.service.CommonService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DataUtil {

    public static String HMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 检查是交易日
     *
     * @param isStock 是否是股票，股票周六日不交易，贵金属周六交易
     * @return 是否是交易日
     */
    public static boolean isTradingDay(boolean isStock) {
        LocalDate date = LocalDate.now();
        return isTradingDay(date, isStock);
    }

    public static boolean isTradingDay(LocalDate date, boolean isStock) {
        final CommonService service = SpringContext.getBean(CommonService.class);
        final List<LocalDate> holidays = service.getHolidays();
        return !holidays.contains(date) && date.getDayOfWeek().getValue() <= (isStock ? 5 : 7);
    }

    public LocalDate getLatestTradeDayLastPeriod(LocalDate data, long amountToSubtract, ChronoUnit unit) {
        data = data.minus(amountToSubtract, unit);
        for (; ; ) {
            if (isTradingDay(data, false)) {
                break;
            }
            data = data.minusDays(1);
        }
        return data;
    }

    public LocalDate getLatestTradeDayLastPeriod(LocalDate data, ChronoUnit unit) {
        return getLatestTradeDayLastPeriod(data, 1, unit);
    }

    public LocalDate getLatestTradeDay() {
        LocalDate data = LocalDate.now();
        return getLatestTradeDayLastPeriod(data, 0, ChronoUnit.DAYS);
    }
}
