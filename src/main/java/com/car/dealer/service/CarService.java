package com.car.dealer.service;

import com.car.dealer.model.Car;
import com.car.dealer.common.Currency;
import com.car.dealer.common.Manufacturer;
import com.car.dealer.validator.CarValidator;
import com.car.dealer.model.CarList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.car.dealer.model.CarList.CarsList;


public class CarService extends Car {
    private final static AtomicInteger nextId = new AtomicInteger();
    public static Car validateCar(Car car){
        Car carValidate = new Car();
        Scanner scanner = new Scanner(System.in);
        CarValidator validator = new CarValidator();

        System.out.println("Provide manufacturer: ");
        carValidate.setManufacturer(validator.validateManufacturer(carValidate.getManufacturer()));
        System.out.print("Provide model: ");
        carValidate.setModel(scanner.next());
        System.out.print("Provide engine: ");
        carValidate.setEngine(validator.validateEngine(carValidate.getEngine()));
        System.out.print("Provide price: ");
        carValidate.setPrice(validator.validatePrice(carValidate.getPrice()));
        System.out.println("Provide currency: ");
        carValidate.setCurrency(validator.validateCurrency(carValidate.getCurrency()));
        return createCar(carValidate);
    }

    public static Car createCar(Car car){
        int i = 0;
        i = nextId.incrementAndGet();
        Car newCar = Car.builder()
                        .ID(i)
                        .manufacturer(car.getManufacturer())
                        .model(car.getModel())
                        .engine(car.getEngine())
                        .price(car.getPrice())
                        .currency(car.getCurrency())
                        .build();
        CarsList.add(newCar);


        System.out.println("\nCar has been created with following details: " + "\nModel: " + car.getModel()
                + " " + "\nEngine: " + car.getEngine() + " " + "\nPrice: " + car.getPrice() + " "
                + car.getCurrency());
        return newCar;
    }



    public static Car findByManufacturer(Car car) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide manufacturer name: ");
        String filterManufacturer = scanner.next().toUpperCase();
        Manufacturer txt = Manufacturer.valueOf(filterManufacturer);
        car.setManufacturer(txt);
        CarsList.stream()
                .filter(x -> x.getManufacturer().equals(txt)) //todo tu moze byc uzyty od razu scanner
                .forEach(e -> System.out.println("ID: " + e.getID() + " " + "Model: " + e.getModel() + " " +
                        "Engine: " + e.getEngine() + " " + "Price: " + e.getPrice() + " " + e.getCurrency()));
                return car;
    }

    public static Car showAllCars(Car car) {
        CarsList.forEach(x -> System.out.println("ID: " + x.getID() + " | " + "Manufacturer: " +
                x.getManufacturer() + " | " + "Model: " + x.getModel() + " | " + "Engine: " + x.getEngine() +
                " | " + "Price: " + x.getPrice() + " " + x.getCurrency()));
        return car;
    }

    public static Car showLoanPrediction(Car car) {
        Scanner scanner = new Scanner(System.in);
        AtomicReference<BigDecimal> onLoanCost = new AtomicReference<>();//todo co to jest atomicreferencje, wez uzyj czegos nromalnego
        AtomicReference<Currency> onLoanCurrency = new AtomicReference<>();
        BigDecimal year = new BigDecimal("12");
        BigDecimal percent = new BigDecimal("0.15");

        System.out.println("Which one you want to take on loan? Please provide ID of car: ");
        int selectedCar = scanner.nextInt();
        CarsList.stream()
                .filter(x -> x.getID() == selectedCar)
                .forEach(x -> {
                    onLoanCost.set(x.getPrice().setScale(2, RoundingMode.HALF_UP));
                    onLoanCurrency.set(x.getCurrency());
                    System.out.println("Selected car: " + "\nManufacturer: " + x.getManufacturer() + " | "
                            + "Model: " + x.getModel() + " | " + "Engine: " + x.getEngine() + " | "
                            + "Price: " + x.getPrice() + " " + x.getCurrency() + "\n");
                });

        System.out.println(onLoanCost);
        BigDecimal selectedCarPrice = onLoanCost.get();
        System.out.println(selectedCarPrice);
        BigDecimal yearCost = selectedCarPrice.divide(year, 0, RoundingMode.DOWN);
        if (selectedCarPrice.compareTo(new BigDecimal("50000")) < 0) {//todo spradziles czy to dziala jak trzeba? uzyj compare to, to chujowo dziala
            percent = BigDecimal.valueOf(0.25).max(percent);
        }
        BigDecimal percentService = yearCost.multiply(percent);
        BigDecimal totalOneYear = yearCost.add(percentService);
        BigDecimal totalTwoYears = totalOneYear.divide(BigDecimal.valueOf(2), 0, RoundingMode.DOWN);
        BigDecimal totalThreeYears = totalOneYear.divide(BigDecimal.valueOf(3), 0, RoundingMode.DOWN);//todo te 4 linie da sie zapisac w jednej lini, wydziel na to metode bedzie czytelne

        System.out.println("One year loan cost per month: " + totalOneYear + " " + onLoanCurrency +
                "\nTwo years loan cost per month: " + totalTwoYears + " " + onLoanCurrency +
                "\nThree years loan cost per month: " + totalThreeYears + " " + onLoanCurrency);

        return car;
    }
}
