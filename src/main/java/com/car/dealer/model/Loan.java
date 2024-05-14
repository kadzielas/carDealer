package com.car.dealer.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Loan {
    private Integer year;
    private BigDecimal percent;
    private BigDecimal yearCostWithoutPercent;
    private BigDecimal yearPercentPrice;
    private BigDecimal totalYearPrice;
}
