package com.car.dealer.model;

import com.car.dealer.common.Currency;
import com.car.dealer.common.Manufacturer;
import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Car {
    private Integer ID;
    private Manufacturer manufacturer;
    private String model;
    private BigDecimal engine;
    private BigDecimal price;
    private Currency currency;
}
