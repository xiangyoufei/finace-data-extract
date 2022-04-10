package com.lysss.finance.dao;

import com.lysss.finance.entity.FinanceIndicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinanceIndicatorRepository extends JpaRepository<FinanceIndicator, Integer> {

    @Query(value = "SELECT concat(code_prefix,'.',code) as fullCode  FROM finance_indicator where valid =true and source=?1", nativeQuery = true)
    List<String> getAllFullCode(String system);

}
