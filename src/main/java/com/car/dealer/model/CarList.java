package com.car.dealer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CarList {
    public static List<Car> listForCarService = new ArrayList<>();
    public static List<Car> queryList;
}
