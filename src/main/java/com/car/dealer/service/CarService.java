package com.car.dealer.service;

import com.car.dealer.common.Manufacturer;
import com.car.dealer.config.HibernateUtil;
import com.car.dealer.model.Car;
import com.car.dealer.model.CarList;
import com.car.dealer.model.Loan;
import com.car.dealer.validator.CarValidator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Scanner;


public class CarService {

    private void saveCar(Car car) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(car);
            session.flush();
            transaction.commit();

        }catch (Exception exception){
            if (transaction != null){
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }
    public Car validateCar() {
        Car carValidate = new Car();
        CarValidator validator = new CarValidator();
        carValidate.setYear(0);

        System.out.println("Provide manufacturer: ");
        carValidate.setManufacturer(validator.validateManufacturer());
        System.out.print("Provide model: ");
        carValidate.setModel(validator.validateModel());
        System.out.print("Provide engine: ");
        carValidate.setEngine(validator.validateEngine(carValidate.getEngine()));
        System.out.println("Provide type of fuel: ");
        carValidate.setFuel(validator.validateFuel());
        System.out.println("Provide year of car: ");
        carValidate.setYear(validator.validateYear(carValidate.getYear()));
        System.out.print("Provide price: ");
        carValidate.setPrice(validator.validatePrice(carValidate.getPrice()));
        System.out.println("Provide currency: ");
        carValidate.setCurrency(validator.validateCurrency());
        return createCar(carValidate);
    }
    private Car createCar(Car car) {
        Car newCar = Car.builder()
                .manufacturer(car.getManufacturer())
                .model(car.getModel())
                .engine(car.getEngine())
                .fuel(car.getFuel())
                .year(car.getYear())
                .price(car.getPrice())
                .currency(car.getCurrency())
                .build();
        CarList.listForCarService.add(newCar);

        System.out.println("\nCar has been created with following details: " + "\nModel: " + newCar.getModel()
                + " " + "\nEngine: " + newCar.getEngine() + " " + newCar.getFuel() + " " + "\nYear: " + newCar.getYear() +
                "\nPrice: " + newCar.getPrice() + " " + newCar.getCurrency());

        saveCar(newCar);
        return newCar;
    }
    private void getCarsFromDataBase(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Car> query = session.createNativeQuery("SELECT * FROM car", Car.class);
            CarList.queryList= query.list();
            CarList.listForCarService = new LinkedList<>(CarList.queryList);
            //TODO nie wiem czy to ma sens
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void showAllCars() {
        getCarsFromDataBase();
        System.out.println("List of all cars:");
        for (Car car : CarList.listForCarService) {
            System.out.println("ID: " + car.getId() + " | "
                    + "Manufacturer: " + car.getManufacturer() + " | " + "Model: " + car.getModel() + " | "
                    + "Engine: " + car.getEngine() + " " + car.getFuel() + " | "
                    + "Year: " + car.getYear() + " | " + "Price: " + car.getPrice() + " " + car.getCurrency());
        }
    }
    public void findCarByManufacturer() {
        System.out.println("Provide manufacturer name: ");
        CarValidator validator = new CarValidator();
        Manufacturer manufacturer = validator.validateManufacturer();

        for (Car selectedManufacturer : CarList.listForCarService) {
            if (selectedManufacturer.getManufacturer().equals(manufacturer)) {
                System.out.println("ID: " + selectedManufacturer.getId() + " " + "Model: " +
                        selectedManufacturer.getModel() + " " + "Engine: " + selectedManufacturer.getEngine() +
                        " " + selectedManufacturer.getFuel() + " " + "Year: " + selectedManufacturer.getYear() + " " +
                        "Price: " + selectedManufacturer.getPrice() + " " + selectedManufacturer.getCurrency());
            }
        }
    }
    public void selectCarToPrediction() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which one you want to take on loan? Please provide ID of car: ");
        int carID = scanner.nextInt();

        Car selectedCarObject = CarList.listForCarService.stream()
                .filter(selectedCar -> selectedCar.getId() == carID)
                .findFirst()
                .orElseThrow(() -> new Exception("ID " + carID + " is not assigned to any car."));

        checkLoanPrice(selectedCarObject);
    }


    private void checkLoanPrice(Car car) {
        Loan loan = new Loan();
        String[] loanTime = {"null", "One year price per month: ",
                "Two years price per month: ",
                "Three years price per month: ",
                "Four years price per month: ",
                "Five years price per month: "};
        loan.setPercent(new BigDecimal("0.08"));
        BigDecimal bigDecimalYear = new BigDecimal(12);

        loan.setYearCostWithoutPercent(new BigDecimal(String.valueOf(car.getPrice()
                .divide(bigDecimalYear, 2, RoundingMode.HALF_UP))));
        loan.setYearPercentPrice(loan.getYearCostWithoutPercent()
                .multiply(loan.getPercent()));
        loan.setTotalYearPrice(loan.getYearCostWithoutPercent()
                .add(loan.getYearPercentPrice()
                        .setScale(2, RoundingMode.HALF_UP)));


        for (int i = 1; i <= 5; i++) {
            BigDecimal resultPrice = loan.getTotalYearPrice().
                    divide(BigDecimal.valueOf(i), 2, RoundingMode.HALF_UP);
            System.out.println(loanTime[i] + resultPrice + " " + car.getCurrency());
        }
    }

//    public HashSet<Car> editCar() throws Exception {
    public void editCar() throws Exception {
        Scanner scanner = new Scanner(System.in);
        CarService availableCarsToEdit = new CarService();
        availableCarsToEdit.showAllCars();
        CarValidator validator = new CarValidator();

        int menu;

        System.out.println("Provide ID of car to edit");
        int carID = scanner.nextInt();

        Car selectedCarObject = CarList.listForCarService.stream()
                .filter(selectedCar -> selectedCar.getId() == carID)
                .findFirst()
                .orElseThrow(() -> new Exception("ID " + carID + " is not assigned to any car."));

        do {
            System.out.println("""
                    What you want to change?
                    1.Manufacturer
                    2.Model
                    3.Engine
                    4.Fuel
                    5.Year
                    6.Price
                    7.Currency
                    0.Back to home page""");
            menu = scanner.nextInt();

            switch (menu) {
                case 1 -> {
                    System.out.println("Provide new manufacturer for selected car: ");
                    selectedCarObject.setManufacturer(validator.validateManufacturer());
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 2 -> {
                    System.out.println("Provide new model for selected car: ");
                    selectedCarObject.setModel(validator.validateModel());
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 3 -> {
                    System.out.println("Provide new engine for selected car: ");
                    selectedCarObject.setEngine(validator.validateEngine(selectedCarObject.getEngine()));
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 4 -> {
                    System.out.println("Provide new type of fuel for selected car: ");
                    selectedCarObject.setFuel(validator.validateFuel());
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 5 -> {
                    System.out.println("Provide new year for selected car: ");
                    selectedCarObject.setYear(validator.validateYear(selectedCarObject.getYear()));
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 6 -> {
                    System.out.println("Provide new price for selected car: ");
                    selectedCarObject.setPrice(validator.validatePrice(selectedCarObject.getPrice()));
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 7 -> {
                    System.out.println("Provide new currency for selected car: ");
                    selectedCarObject.setCurrency(validator.validateCurrency());
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 0 -> System.out.println("Back to home page");
            }
        } while (menu != 0);


//        return saveApplicationFile();
    }

//    public HashSet<Car> removeCar() throws Exception {
    public void removeCar() throws Exception {
        CarService availableCarsToRemove = new CarService();
        availableCarsToRemove.showAllCars();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Provide ID of car to remove");
        int carID = scanner.nextInt();

        Car selectedCarObject = CarList.listForCarService.stream()
                .filter(selectedCar -> selectedCar.getId() == carID)
                .findFirst()
                .orElseThrow(() -> new Exception("ID " + carID + " is not assigned to any car."));

        selectedCarObject.setId(null);
        selectedCarObject.setManufacturer(null);
        selectedCarObject.setModel(null);
        selectedCarObject.setEngine(null);
        selectedCarObject.setYear(null);
        selectedCarObject.setPrice(null);
        selectedCarObject.setCurrency(null);

//        return saveApplicationFile();
    }
}

