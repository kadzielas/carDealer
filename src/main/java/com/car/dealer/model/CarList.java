package com.car.dealer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;

@Data
@AllArgsConstructor
public class CarList {
    public static HashSet<Car> listForCarService = new HashSet<>();
}
