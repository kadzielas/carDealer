package com.car.dealer.service;

import com.car.dealer.model.Car;
import com.car.dealer.common.Currency;
import com.car.dealer.model.CarList;
import com.car.dealer.validator.CarValidator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.car.dealer.model.CarList.CarsList;

public class CarService extends Car {
    private final static AtomicInteger nextId = new AtomicInteger();
    public static void addCarForm(){
        CarValidator validator = new CarValidator();
        Scanner scanner = new Scanner(System.in);
        CarService carValidate = new CarService();//todo carService -> car

        int i = 0;
        //todo wydziel walidacje i dodawanie nowego auta do nowej metody
        i = nextId.incrementAndGet();
        System.out.println("Provide manufacturer: ");
        carValidate.setManufacturer(scanner.next());//todo brak walidacji na manufactorure
        System.out.print("Provide model: ");//todo brak walidacji na model
        carValidate.setModel(scanner.next());
        System.out.print("Provide engine: ");
        carValidate.setEngine(validator.validateEngine(carValidate.getEngine()));
        System.out.print("Provide price: ");
        carValidate.setPrice(validator.validatePrice(carValidate.getPrice()));
        System.out.println("Provide currency: ");
        carValidate.setCurrency(validator.validateCurrency(carValidate.getCurrency()));
        Currency validatedCurrency = carValidate.getCurrency();//raz setujesz zmienne, a tu zapisujesz do innej czemu?

        Car newCar = Car.builder()
                        .ID(i)
                        .manufacturer(carValidate.getManufacturer())
                        .model(carValidate.getModel())
                        .engine(carValidate.getEngine())
                        .price(carValidate.getPrice())
                        .currency(validatedCurrency)// wszystko na getach a tu inna zmienna?
                        .build();
        CarsList.add(newCar);

        System.out.println("\nCar has been created with following details: " + "\nModel: " + carValidate.getModel()
                + " " + "\nEngine: " + carValidate.getEngine() + " " + "\nPrice: " + carValidate.getPrice() + " "
                + carValidate.getCurrency());
    }


    public static void findByManufacturer() { //todo voidy

        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide manufacturer name: ");
        String filterManufacturer = scanner.next();
        CarsList.stream()
                .filter(car -> car.getManufacturer().equalsIgnoreCase(filterManufacturer))//todo tu moze byc uzyty od razu scanner
                .forEach(car -> System.out.println("ID: " + car.getID() + " " + "Model: " + car.getModel() + " " +
                        "Engine: " + car.getEngine() + " " + "Price: " + car.getPrice() + " " + car.getCurrency()));
    }

    public static void showAllCars() {//todo voidy
        CarsList.forEach(car -> System.out.println("ID: " + car.getID() + " | " + "Manufacturer: " +
                car.getManufacturer() + " | " + "Model: " + car.getModel() + " | " + "Engine: " + car.getEngine() +
                " | " + "Price: " + car.getPrice() + " " + car.getCurrency()));
    }

    public static void showLoanPrediction() { //todo voidy
        Scanner scanner = new Scanner(System.in);
        AtomicReference<BigDecimal> onLoanCost = new AtomicReference<>();//todo co to jest atomicreferencje, wez uzyj czegos nromalnego
        AtomicReference<Currency> onLoanCurrency = new AtomicReference<>();
        BigDecimal year = new BigDecimal("12");
        BigDecimal percent = new BigDecimal("0.15");

        System.out.println("Which one you want to take on loan? Please provide ID of car: ");
        int selectedCar = scanner.nextInt();
        CarsList.stream()
                .filter(car -> car.getID() == selectedCar)
                .forEach(car -> {
                    onLoanCost.set(car.getPrice().setScale(2, RoundingMode.HALF_UP));
                    onLoanCurrency.set(car.getCurrency());
                    System.out.println("Selected car: " + "\nManufacturer: " + car.getManufacturer() + " | "
                            + "Model: " + car.getModel() + " | " + "Engine: " + car.getEngine() + " | "
                            + "Price: " + car.getPrice() + " " + car.getCurrency() + "\n");
                });

        BigDecimal selectedCarPrice = onLoanCost.get();
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
    }
}
