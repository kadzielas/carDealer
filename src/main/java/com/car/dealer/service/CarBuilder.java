package com.car.dealer.service;

import com.car.dealer.model.Car;
import com.car.dealer.validator.CarValidator;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CarBuilder extends CarService { //TODO merge into carService

    public static AtomicInteger nextId = new AtomicInteger(); //to jest do wyjebania

    public void addCar(CarBuilder createdCar){
        CarBuilder car = new CarBuilder();
        Car car1 = new Car();
        getCarsList().add(createdCar);
    }

    public static void Car(){
        CarValidator validator = new CarValidator();
        Scanner scanner = new Scanner(System.in);
        CarBuilder car = new CarBuilder();

        int i = 0;
        i = nextId.incrementAndGet();
        car.setID(i);
        System.out.println("Provide manufacturer: ");
        car.setManufacturer(scanner.next());
        System.out.print("Provide model: ");
        car.setModel(scanner.next());
        System.out.print("Provide engine: ");
        validator.validationEngine();
        car.setEngine(validator.getEngine());
        System.out.print("Provide price: ");
        validator.validationPrice();
        car.setPrice(validator.getPrice());
        System.out.println("Provide currency: ");
        car.setCurrency(validator.validationCurrency(car.getCurrency()));
        car.addCar(car);

        System.out.println("\nCar has been created with following details: " + "\nModel: " + car.getModel() + " " + "\nEngine: " + car.getEngine() + " " + "\nPrice: " + car.getPrice() + " " + car.currency);
    }

    public void test() {
        String example1;
        String example2;
        String example3;
        //Scanner zwaliduj mi example1
        // tu cos sie dzieje walidacją
        example1 = validateExample1(inputUsera);
        //Scanner zwaliduj mi example2
        // tu cos sie dzieje walidacją
        //Scanner zwaliduj mi example3
        // tu cos sie dzieje walidacją
        //car.setPrice(example1);
        Car car = new Car();
        car.set
    }
}
