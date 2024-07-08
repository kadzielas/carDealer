package com.car.dealer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public class CarList {
    public static LinkedList<Car> listForCarService;
    public static List<Car> queryList;
}
