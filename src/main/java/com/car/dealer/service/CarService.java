package com.car.dealer.service;

import com.car.dealer.common.Manufacturer;
import com.car.dealer.model.Car;
import com.car.dealer.model.CarList;
import com.car.dealer.model.Loan;
import com.car.dealer.validator.CarValidator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class CarService {

    public Car validateCar() {
        Car carValidate = new Car();
        CarValidator validator = new CarValidator();

        System.out.println("Provide manufacturer: ");
        carValidate.setManufacturer(validator.validateManufacturer());
        System.out.print("Provide model: ");
        carValidate.setModel(String.valueOf(validator.validateModel()));
        System.out.print("Provide engine: ");
        carValidate.setEngine(validator.validateEngine(carValidate.getEngine()));
        System.out.println("Provide type of fuel: ");
        carValidate.setFuel(validator.validateFuel());
        System.out.print("Provide price: ");
        carValidate.setPrice(validator.validatePrice(carValidate.getPrice()));
        System.out.println("Provide currency: ");
        carValidate.setCurrency(validator.validateCurrency());
        return createCar(carValidate);
    }

    private Car createCar(Car car) {
        loadApplicationFile();
        Car newCar = Car.builder()
                .ID(loadLastId() + 1)
                .manufacturer(car.getManufacturer())
                .model(car.getModel())
                .engine(car.getEngine())
                .fuel(car.getFuel())
                .price(car.getPrice())
                .currency(car.getCurrency())
                .build();
        CarList.listForCarService.add(newCar);

        System.out.println("\nCar has been created with following details: " + "\nModel: " + car.getModel()
                + " " + "\nEngine: " + car.getEngine() + " " + car.getFuel() + " " + "\nPrice: " + car.getPrice() + " "
                + car.getCurrency());

        saveApplicationFile();
        saveLastId(newCar.getID());
        return newCar;
    }

    private static int loadLastId() {
        int lastId = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\lastId.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            lastId = (int) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error " + e.getMessage());
        }
        return lastId;
    }

    private static void saveLastId(int lastId) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src\\main\\resources\\lastId.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(lastId);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private static HashSet<Car> saveApplicationFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src\\main\\resources\\carDealer.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Car savedCars : CarList.listForCarService) {
                objectOutputStream.writeObject(savedCars);
            }
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException ioe) {
            System.err.println("Error saving to file");
        }
        return CarList.listForCarService;
    }

    public static HashSet<Car> loadApplicationFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\carDealer.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                Car car = (Car) objectInputStream.readObject();
                CarList.listForCarService.add(car);
            }
            objectInputStream.close();
        } catch (Exception ex) {
            System.err.println("Error loading file " + ex.getMessage());
        }
        return CarList.listForCarService;
    }


    public void findByManufacturer() {

        System.out.println("Provide manufacturer name: ");
        CarValidator validator = new CarValidator();
        Manufacturer manufacturer = validator.validateManufacturer();

        for (Car selectedManufacturer : CarList.listForCarService) {
            if (selectedManufacturer.getManufacturer().equals(manufacturer)) {
                System.out.println("ID: " + selectedManufacturer.getID() + " " + "Model: " +
                        selectedManufacturer.getModel() + " " + "Engine: " + selectedManufacturer.getEngine() +
                        " " + selectedManufacturer.getFuel() + " " + "Price: " + selectedManufacturer.getPrice() +
                        " " + selectedManufacturer.getCurrency());
            }
        }

    }

    public void showAllCars() {
        List<Car> sortedListOfCars = new ArrayList<>(CarList.listForCarService);
        sortedListOfCars.sort(Comparator.comparingInt(Car::getID));

        sortedListOfCars.forEach(sortedCars -> System.out.println("ID: " + sortedCars.getID() + " | " + "Manufacturer: " +
                sortedCars.getManufacturer() + " | " + "Model: " + sortedCars.getModel() + " | " + "Engine: " + sortedCars.getEngine() +
                " " + sortedCars.getFuel() + " | " + "Price: " + sortedCars.getPrice() + " " + sortedCars.getCurrency()));
    }

    public void selectCarToPrediction() throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which one you want to take on loan? Please provide ID of car: ");
        int carID = scanner.nextInt();

        Car selectedCarObject = CarList.listForCarService.stream()
                .filter(selectedCar -> selectedCar.getID() == carID)
                .findFirst()
                .orElseThrow(() -> new Exception("ID " + carID + " is not assigned to any car."));

        checkLoanPrice(selectedCarObject);
            }



    private Loan checkLoanPrice(Car car){
        Loan loan = new Loan();
        String[] loanTime = {"null", "One year price per month: ",
                "Two years price per month: ",
                "Three years price per month: ",
                "Four years price per month: ",
                "Five years price per month: "};
        loan.setYear(new BigDecimal(12));
        loan.setPercent(new BigDecimal("0.15"));

        loan.setYearCostWithoutPercent(new BigDecimal(String.valueOf(car.getPrice()
                .divide(loan.getYear(), 2, BigDecimal.ROUND_HALF_UP))));
        loan.setYearPercentPrice(loan.getYearCostWithoutPercent()
                .multiply(loan.getPercent()));
        loan.setTotalYearPrice(loan.getYearCostWithoutPercent()
                .add(loan.getYearPercentPrice()
                .setScale(2, BigDecimal.ROUND_HALF_UP)));


        for (int i = 1; i <= 5; i++){
            BigDecimal resultPrice = loan.getTotalYearPrice().
                    divide(BigDecimal.valueOf(i), 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(loanTime[i] + resultPrice + " " + car.getCurrency());
        }
        return loan;
    }

    public HashSet<Car> editCar() {
        Scanner scanner = new Scanner(System.in);
        CarService availableCarsToEdit = new CarService();
        availableCarsToEdit.showAllCars();

        CarValidator validator = new CarValidator();

        int menu;

        System.out.println("Provide ID of car to edit");
        int selectedCar = scanner.nextInt();

        Iterator<Car> iterator = CarList.listForCarService.iterator();

        while (iterator.hasNext()) {

            Car carIterator = iterator.next();
            if (carIterator.getID().equals(selectedCar)) {
                do {
                    System.out.println("""
                        What you want to change?
                        1.Manufacturer
                        2.Model
                        3.Engine
                        4.Fuel
                        5.Price
                        6.Currency
                        0.Back to home page""");
                    menu = scanner.nextInt();

                    switch (menu) {
                        case 1 -> {
                            System.out.println("Provide new manufacturer for selected car: ");
                            carIterator.setManufacturer(validator.validateManufacturer());
                            System.out.println("Change has been saved");
                            System.out.println("\n");
                        }
                        case 2 -> {
                            System.out.println("Provide new model for selected car: ");
                            String editModel = scanner.next();
                            carIterator.setModel(editModel);
                            System.out.println("Change has been saved");
                            System.out.println("\n");
                        }
                        case 3 -> {
                            System.out.println("Provide new engine for selected car: ");
                            carIterator.setEngine(validator.validateEngine(carIterator.getEngine()));
                            System.out.println("Change has been saved");
                            System.out.println("\n");
                        }
                        case 4 -> {
                            System.out.println("Provide new type of fuel for selected car: ");
                            carIterator.setFuel(validator.validateFuel());
                            System.out.println("Change has been saved");
                            System.out.println("\n");
                        }
                        case 5 -> {
                            System.out.println("Provide new price for selected car: ");
                            carIterator.setPrice(validator.validatePrice(carIterator.getPrice()));
                            System.out.println("Change has been saved");
                            System.out.println("\n");
                        }
                        case 6 -> {
                            System.out.println("Provide new currency for selected car: ");
                            carIterator.setCurrency(validator.validateCurrency());
                            System.out.println("Change has been saved");
                            System.out.println("\n");
                        }
                        case 0 -> System.out.println("Back to home page");
                    }
                } while (menu != 0);
            } else {
                System.out.println("Provided ID is not assigned to any of car.");
                //TODO dlaczego napis jest 7 razy jak to jest w else a if nie powinien się wykonać XD
            }
        }

        return saveApplicationFile();
    }

    public HashSet<Car> removeCar() {
        CarService availableCarsToRemove = new CarService();
        availableCarsToRemove.showAllCars();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Provide ID of car to remove");
        int selectedCar = scanner.nextInt();

        Iterator<Car> iterator = CarList.listForCarService.iterator();
        while (iterator.hasNext()) {

            Car carIterator = iterator.next();
            if (carIterator.getID().equals(selectedCar)) {
                iterator.remove();
                carIterator.setID(null);
                carIterator.setManufacturer(null);
                carIterator.setModel(null);
                carIterator.setEngine(null);
                carIterator.setPrice(null);
                carIterator.setCurrency(null);
            }
        }
        return saveApplicationFile();
    }
}

