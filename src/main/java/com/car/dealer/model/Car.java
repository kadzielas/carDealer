package com.car.dealer.model;

import com.car.dealer.common.Currency;
import lombok.*;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Car {
    public Integer ID;
    public String manufacturer;
    private String model;
    public BigDecimal engine;
    public BigDecimal price;
    public Currency currency;
}
