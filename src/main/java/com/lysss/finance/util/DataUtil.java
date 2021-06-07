package com.lysss.finance.util;

import com.lysss.finance.service.CommonService;

import java.time.LocalDate;
import java.util.List;

public class DataUtil {

    public static String HMS="yyyy-MM-dd HH:mm:ss";

    public static boolean isTradingDay(boolean isStock) {
        LocalDate date = LocalDate.now();
        final CommonService service = SpringContext.getBean(CommonService.class);
        final List<LocalDate> holidays = service.getHolidays();
        return !holidays.contains(date) && date.getDayOfWeek().getValue() <= (isStock ? 5 : 7);
    }
}
