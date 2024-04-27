package com.car.dealer.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public record CarList(HashSet<Car> carList) {
         public static HashSet<Car> listOfCars = new HashSet<>();

}
