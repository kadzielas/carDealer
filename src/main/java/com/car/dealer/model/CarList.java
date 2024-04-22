package com.car.dealer.model;

import java.util.ArrayList;
import java.util.List;

public record CarList(List<Car> carList) {
         public static List<Car> CarsList = new ArrayList<>();
    public List<Car> getCarsList() {
        return CarsList;
    }

    //todo poco to?? poczytaj o rekordach i wyjeb to
}
