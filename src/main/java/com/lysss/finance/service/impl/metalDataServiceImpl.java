package com.lysss.finance.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lysss.finance.dao.FinanceDataRepository;
import com.lysss.finance.entity.FinanceData;
import com.lysss.finance.entity.MetalFinanceData;
import com.lysss.finance.service.DataSourceService;
import com.lysss.finance.util.DataUtil;
import com.lysss.finance.util.HttpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service("metalData")
public class metalDataServiceImpl implements DataSourceService {

    @Value("${resources.metal.url}")
    private String url;

    @Resource
    private FinanceDataRepository dataRepository;


    @Override
    public void pullAndSaveFundData() {
        String body = "Area_code=1001&trademode=1&proIdsIn=&isFirstTime=1&tranCode=A00500";
        final HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Host", "mybank.icbc.com.cn");
          String s = HttpUtil.postMethod(url, body, headers);
        s=s.replaceAll("%","");
        final JSONObject jsonObject = JSONObject.parseObject(s);
        if(jsonObject.getInteger("TranErrorCode")==0){
            final JSONArray market = jsonObject.getJSONArray("market");
            LocalDateTime now =LocalDateTime.now();
            final String sysdate = jsonObject.getString("sysdate");
            final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DataUtil.HMS);
            final LocalDateTime dateTime = LocalDateTime.parse(sysdate,timeFormatter);
            final LocalDate today = now.toLocalDate();
            final List<MetalFinanceData> metalFinanceData = market.toJavaList(MetalFinanceData.class);
            final List<FinanceData> collect = metalFinanceData.stream().map(this::convert2FinanceData)
                    .peek(item->item.setDate(today))
                    .peek(item->item.setDataTime(now))
                    .peek(item->item.setTime(dateTime))
                    .collect(Collectors.toList());
            dataRepository.saveAll(collect);
        }
    }

    private  FinanceData convert2FinanceData(MetalFinanceData metalFinanceData) {
        final FinanceData financeData = new FinanceData();
        BeanUtils.copyProperties(metalFinanceData,financeData);
        return financeData;
    }

    @Override
    public List<FinanceData> pullFundData() {
        return null;
    }

    @Override
    public void notifyEveryDayBeforeClose() {

    }
}
