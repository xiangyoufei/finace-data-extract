package com.lysss.finance.service;

import java.time.LocalDate;
import java.util.List;

public interface CommonService {

    List<LocalDate> getHolidays();

    Object executeByReflect(String beanName, String methodName, Object... params);
}
