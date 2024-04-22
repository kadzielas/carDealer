package com.car.dealer.model;

import com.car.dealer.common.Currency;
import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Car {
    private Integer ID;
    private String manufacturer;
    private String model;
    private BigDecimal engine;
    private BigDecimal price;
    private Currency currency;
}
