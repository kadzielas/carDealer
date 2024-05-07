package com.car.dealer.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Loan {
    private final BigDecimal year = new BigDecimal("12");
    private final BigDecimal percent = new BigDecimal("0.15");
    private final String loanTime[] = {"null", "One year price: ", "Two years price: ", "Three years price: "};

}
