package com.lysss.finance.service.impl;

import com.lysss.finance.entity.FinanceData;
import com.lysss.finance.service.DataSourceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@Service("metalData")
public class metalDataServiceImpl implements DataSourceService {

    @Value("${resources.metal.url}")
    private String url;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void pullAndSaveFundData() {
        final HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
//        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        String body="Area_code=1001&GoldIndexTradeMode=1&proIdsIn=&isFirstTime=1&step=3&tranCode=A00508";
        final HttpEntity<String> httpEntity = new HttpEntity<>(body,httpHeaders);
        final ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, "Area_code=1001&GoldIndexTradeMode=1&proIdsIn=&isFirstTime=1&step=3&tranCode=A00508", String.class);
        System.out.println(responseEntity.getBody());

    }

    @Override
    public List<FinanceData> pullFundData() {
        return null;
    }

    @Override
    public void notifyEveryDayBeforeClose() {

    }
}
