package com.lysss.finance.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Table
@Entity
public class Holiday {

    @Id
    private int id;

    private String name;

    private LocalDate date;

    private int year;

}
