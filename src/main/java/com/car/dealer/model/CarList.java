package com.car.dealer.model;

import java.util.ArrayList;
import java.util.List;

public record CarList(List<Car> carList) {
         public static List<Car> CarsList = new ArrayList<>();

}
