package com.car.dealer.model;

import com.car.dealer.common.Currency;
import com.car.dealer.common.Fuel;
import com.car.dealer.common.Manufacturer;
import com.car.dealer.common.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car3")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "manufacturer", nullable = false)
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "model", nullable = false)
    private Model model;

    @Column(name = "engine", nullable = false)
    private BigDecimal engine;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fuel", nullable = false)
    private Fuel fuel;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "currency", nullable = false)
    private Currency currency;
}
