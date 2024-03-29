package com.lysss.finance.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "finance_indicator")
public class FinanceIndicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String code;

    private String codePrefix;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean valid;

    private String source;
}
