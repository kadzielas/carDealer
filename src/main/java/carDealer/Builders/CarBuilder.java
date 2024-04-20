package carDealer.Builders;

import carDealer.Car.Cars;

import java.util.Scanner;

public class CarBuilder extends Cars {

    public void addCar(CarBuilder createdCar){
        CarBuilder car = new CarBuilder();
        getCarsList().add(createdCar);
    }

    public static void Car(){

        Validation validator = new Validation();
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
        car.setEngine2(validator.getEngine2());
        System.out.print("Provide price: ");
        validator.validationPrice();
        car.setPrice2(validator.getPrice2());
        System.out.println("Provide currency: ");
        validator.validationCurrency();
        car.setCurrency(validator.currency);
        car.addCar(car);

        System.out.println("\nCar has been created with following details: " + "\nModel: " + car.getModel() + " " + "\nEngine: " + car.getEngine2() + " " + "\nPrice: " + car.getPrice2() + " " + car.currency);
    }
}
