package com.car.dealer.service;

import com.car.dealer.model.Car;
import com.car.dealer.common.Manufacturer;
import com.car.dealer.validator.CarValidator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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

    public static Manufacturer testScanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide manufacturer name: ");
        String filterManufacturer = scanner.next().toUpperCase();
        Manufacturer manufacturer = Manufacturer.valueOf(filterManufacturer);
        return manufacturer;
    }

    public static Car findByManufacturer(Car car) {
        CarsList.stream()
                .filter(x -> x.getManufacturer().equals(testScanner()))
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

    public static Car selectCarToPrediction(Car car) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which one you want to take on loan? Please provide ID of car: ");
        int selectedCar = scanner.nextInt();

        Car selectedCarObject = CarsList.stream()
                .filter(x -> x.getID() == selectedCar)
                .findFirst()
                .orElse(null);

        if (selectedCarObject != null) {

            System.out.println("Selected car: " + "\nManufacturer: " + selectedCarObject.getManufacturer() + " | "
                    + "Model: " + selectedCarObject.getModel() + " | " + "Engine: " + selectedCarObject.getEngine() + " | "
                    + "Price: " + selectedCarObject.getPrice() + " " + selectedCarObject.getCurrency() + "\n");
        }
        return checkLoanPrice(selectedCarObject);

    }

    public static Car checkLoanPrice(Car car){
        BigDecimal year = new BigDecimal("12");
        BigDecimal percent = new BigDecimal("0.15");
        BigDecimal yearCost = car.getPrice().divide(year, 2, RoundingMode.HALF_UP);
        BigDecimal totalCost = yearCost.multiply(percent);
        BigDecimal totalOneYear = yearCost.add(totalCost).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalTwoYeras = totalOneYear.divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);
        BigDecimal totalThreeYeras = totalOneYear.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);

        System.out.println("One year loan cost per month: " + totalOneYear + " " + car.getCurrency() +
                "\nTwo years loan cost per month: " + totalTwoYeras + " " + car.getCurrency() +
                "\nThree years loan cost per month: " + totalThreeYeras + " " + car.getCurrency());
        return car;
    }
}
