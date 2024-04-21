package com.car.dealer.service;

import com.car.dealer.model.Car;
import com.car.dealer.common.Currency;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class CarService {


    private static List<CarBuilder> CarsList = new ArrayList<>();

    public List<CarBuilder> getCarsList() {
        return CarsList;
    }


    public static void findByManufacturer(Car car) {

        var dupa = "XD";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide manufacturer name: ");
        String filterManufacturer = scanner.next();
        CarsList.stream()
                .filter(car -> car.getManufacturer().equalsIgnoreCase(filterManufacturer))
                .forEach(car -> System.out.println("ID: " + car.getID() + " " + "Model: " + car.getModel() + " " + "Engine: " + car.getEngine() + " " + "Price: " + car.getPrice() + " " + car.currency));
    }

    public static void showAllCars() {
        CarsList.forEach(car -> System.out.println("ID: " + car.getID() + " | " + "Manufacturer: " + car.getManufacturer() + " | " + "Model: " + car.getModel() + " | " + "Engine: " + car.getEngine() + " | " + "Price: " + car.getPrice() + " " + car.currency));
    }

    public static void showLoanPrediction() {
        Scanner scanner = new Scanner(System.in);
        AtomicReference<BigDecimal> onLoanCost = new AtomicReference<>();
        AtomicReference<Currency> onLoanCurrency = new AtomicReference<>();
        BigDecimal year = new BigDecimal("12");
        BigDecimal percent = new BigDecimal("0.15");

        System.out.println("Which one you want to take on loan? Please provide ID of car: ");
        int selectedCar = scanner.nextInt();
        CarsList.stream()
                .filter(car -> car.getID() == selectedCar)
                .forEach(car -> {
                    onLoanCost.set(car.getPrice().setScale(2, RoundingMode.HALF_UP));
                    onLoanCurrency.set(car.currency);
                    System.out.println("Selected car: " + "\nManufacturer: " + car.getManufacturer() + " | "
                            + "Model: " + car.getModel() + " | " + "Engine: " + car.getEngine() + " | "
                            + "Price: " + car.getPrice() + " " + car.currency + "\n");
                });

        BigDecimal selectedCarPrice = onLoanCost.get();
        BigDecimal yearCost = selectedCarPrice.divide(year, 0, RoundingMode.DOWN);
        if (selectedCarPrice.compareTo(new BigDecimal("50000")) < 0) {
            percent = BigDecimal.valueOf(0.25).max(percent);
        }
        BigDecimal percentService = yearCost.multiply(percent);
        BigDecimal totalOneYear = yearCost.add(percentService);
        BigDecimal totalTwoYears = totalOneYear.divide(BigDecimal.valueOf(2), 0, RoundingMode.DOWN);
        BigDecimal totalThreeYears = totalOneYear.divide(BigDecimal.valueOf(3), 0, RoundingMode.DOWN);

        System.out.println("One year loan cost per month: " + totalOneYear + " " + onLoanCurrency +
                "\nTwo years loan cost per month: " + totalTwoYears + " " + onLoanCurrency +
                "\nThree years loan cost per month: " + totalThreeYears + " " + onLoanCurrency);
    }
}
