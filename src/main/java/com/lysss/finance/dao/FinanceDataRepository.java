package com.lysss.finance.dao;

import com.lysss.finance.entity.FinanceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceDataRepository extends JpaRepository<FinanceData,Integer> {
}
