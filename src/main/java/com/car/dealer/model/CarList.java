package com.car.dealer.model;

import java.util.HashSet;

public record CarList(HashSet<Car> carList) {
    private static HashSet<Car> generalListOfCars = new HashSet<>();
    public static HashSet<Car> listForCarService = CarList.generalListOfCars;
}
