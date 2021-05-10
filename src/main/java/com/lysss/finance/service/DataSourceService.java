package com.lysss.finance.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lysss.finance.dao.FinanceIndicatorRepository;
import com.lysss.finance.entity.FinanceData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataSourceService {


    @Value("${schedule.fund.url}")
    private String fundUrl;

    @Resource
    private RestTemplate restTemplate;


    @Resource
    private FinanceIndicatorRepository financeIndicatorRepository;


    public List<FinanceData> pullFundData() {

        final List<String> fullCode = financeIndicatorRepository.getAllFullCode();
        final String allCode = fullCode.stream().collect(Collectors.joining(","));
        String url = fundUrl.replace("{secids}", allCode);
        final LocalDateTime now = LocalDateTime.now();
        final long time = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(url.replaceAll("\\{tm\\}", time + ""), String.class);
        List<FinanceData> financeData = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            final JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
            final String string = jsonObject.getJSONObject("data").getString("diff");
            financeData = JSONArray.parseArray(string, FinanceData.class).stream()
                    .peek(FinanceData::dealPrecision)
                    .peek(item -> item.setDataTime(now))
                    .peek(item -> item.setTime(now))
                    .collect(Collectors.toList());
            System.out.println(financeData);
        }
        return Optional.ofNullable(financeData).orElse(Collections.EMPTY_LIST);
    }

}
