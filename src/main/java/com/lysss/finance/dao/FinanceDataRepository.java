package com.lysss.finance.dao;

import com.lysss.finance.entity.FinanceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinanceDataRepository extends JpaRepository<FinanceData,Integer> {

    @Query(nativeQuery=true,value = "SELECT *  FROM ( SELECT *, row_number() over (PARTITION BY CODE ORDER BY time DESC ) AS rm FROM `finance_data` where date=CURDATE() ) a  WHERE a.rm = 1")
    List<FinanceData>  findALLIndicatorCloseValue();
}
