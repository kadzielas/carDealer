package com.car.dealer.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Loan {
    private final BigDecimal year = new BigDecimal("12");
    private final BigDecimal percent = new BigDecimal("0.15");

}
