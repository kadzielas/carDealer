package com.car.dealer.model;

import java.util.HashSet;

public record CarList(HashSet<Car> carList) {
    //todo dodales generalListOfCars, tylko po to zeby uzyc tego samego linijke wyzej? przeróbmy to na normalną klasę
    //modelowa jak Car, nie będziemy Ci mieszać tych rekordów
    private static HashSet<Car> generalListOfCars = new HashSet<>();
    public static HashSet<Car> listForCarService = CarList.generalListOfCars;
}
