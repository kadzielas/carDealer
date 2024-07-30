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
import java.util.Scanner;


public class CarService {
    final private Scanner scanner = new Scanner(System.in);

    //saveCar - function to save car into database, it is used only in createCar function
    private void saveCar(Car car) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(car);
            session.flush();
            transaction.commit();

        } catch (Exception saveCarException) {
            if (transaction != null) {
                transaction.rollback();
            }
            saveCarException.printStackTrace();
        }
    }

    //validateCar - method to validate information about car before build new object
    public void validateCar() {
        Car carValidate = new Car();
        CarValidator validator = new CarValidator();


        System.out.println("Provide manufacturer: ");
        carValidate.setManufacturer(validator.validateManufacturer());
        System.out.print("Provide model: ");
        carValidate.setModel(validator.validateModel());
        System.out.print("Provide engine: ");
        carValidate.setEngine(validator.validateEngine(carValidate.getEngine()));
        System.out.println("Provide type of fuel: ");
        carValidate.setFuel(validator.validateFuel());
        System.out.println("Provide year of car: ");
        carValidate.setYear(validator.validateYear());
        System.out.print("Provide price: ");
        carValidate.setPrice(validator.validatePrice(carValidate.getPrice()));
        System.out.println("Provide currency: ");
        carValidate.setCurrency(validator.validateCurrency());
        createCar(carValidate);
    }

    //createCar - function takes every validated information about new car and use it to build new object (car). It's running instantly after validateCar method
    private void createCar(Car car) {
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
    }

    //getAllCarsFromDataBase - the function is in used by showAvailableCars (CarFacade class) and during launching application (Main class)
    public void getAllCarsFromDataBase() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Car> query = session.createNativeQuery("SELECT * FROM car", Car.class);
            CarList.queryList = query.list();
            CarList.listForCarService = CarList.queryList;
        } catch (Exception getAllCarsException) {
            getAllCarsException.printStackTrace();
        }
    }

    //showAvailableCars - method to show list of available cars, can be found in CarFacade class
    public void showAvailableCars() {
        getAllCarsFromDataBase();
        System.out.println("List of all cars:");
        for (Car car : CarList.listForCarService) {
            System.out.println("ID: " + car.getId() + " | "
                    + "Manufacturer: " + car.getManufacturer() + " | " + "Model: " + car.getModel() + " | "
                    + "Engine: " + car.getEngine() + " " + car.getFuel() + " | "
                    + "Year: " + car.getYear() + " | " + "Price: " + car.getPrice() + " " + car.getCurrency());
        }
    }

    //findCarByManufacturer - function to find specific car by manufacturer, it can be run from CarFacade class
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

    //selectCarToPrediction - method to choose one of available cars. The method is in used only by checkLoanPrice. Can be found in CarFacade class
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

    //checkLoanPrice - method to calculate loan price of car by year, it's running instantly after selectCarToPrediction method
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

    //editCar - the function is used to update information about car like manufacturer or price, it can be run from Main class
    public void editCar() {
        Scanner scanner = new Scanner(System.in);
        CarService availableCarsToEdit = new CarService();
        availableCarsToEdit.showAvailableCars();
        CarValidator validator = new CarValidator();
        Transaction transaction = null;

        int menu;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
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
                        Car car = session.get(Car.class, selectedCarObject.getId());
                        car.setManufacturer(selectedCarObject.getManufacturer());
                        session.update(car);
                        System.out.println("Change has been saved");
                        System.out.println("\n");
                    }
                    case 2 -> {
                        System.out.println("Provide new model for selected car: ");
                        selectedCarObject.setModel(validator.validateModel());
                        Car car = session.get(Car.class, selectedCarObject.getId());
                        car.setModel(selectedCarObject.getModel());
                        session.update(car);
                        System.out.println("Change has been saved");
                        System.out.println("\n");
                    }
                    case 3 -> {
                        System.out.println("Provide new engine for selected car: ");
                        selectedCarObject.setEngine(validator.validateEngine(selectedCarObject.getEngine()));
                        Car car = session.get(Car.class, selectedCarObject.getId());
                        car.setEngine(selectedCarObject.getEngine());
                        session.update(car);
                        System.out.println("Change has been saved");
                        System.out.println("\n");
                    }
                    case 4 -> {
                        System.out.println("Provide new type of fuel for selected car: ");
                        selectedCarObject.setFuel(validator.validateFuel());
                        Car car = session.get(Car.class, selectedCarObject.getId());
                        car.setFuel(selectedCarObject.getFuel());
                        session.update(car);
                        System.out.println("Change has been saved");
                        System.out.println("\n");
                    }
                    case 5 -> {
                        System.out.println("Provide new year for selected car: ");
                        selectedCarObject.setYear(validator.validateYear());
                        Car car = session.get(Car.class, selectedCarObject.getId());
                        car.setYear(selectedCarObject.getYear());
                        session.update(car);
                        System.out.println("Change has been saved");
                        System.out.println("\n");
                    }
                    case 6 -> {
                        System.out.println("Provide new price for selected car: ");
                        selectedCarObject.setPrice(validator.validatePrice(selectedCarObject.getPrice()));
                        Car car = session.get(Car.class, selectedCarObject.getId());
                        car.setPrice(selectedCarObject.getPrice());
                        session.update(car);
                        System.out.println("Change has been saved");
                        System.out.println("\n");
                    }
                    case 7 -> {
                        System.out.println("Provide new currency for selected car: ");
                        selectedCarObject.setCurrency(validator.validateCurrency());
                        Car car = session.get(Car.class, selectedCarObject.getId());
                        car.setCurrency(selectedCarObject.getCurrency());
                        session.update(car);
                        System.out.println("Change has been saved");
                        System.out.println("\n");
                    }
                    case 0 -> System.out.println("Back to home page");
                }
            } while (menu != 0);

            session.flush();
            transaction.commit();

        } catch (Exception editCarException) {
            editCarException.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    //removeCar - it's function to remove existed car, it can be run from Main class
    public void removeCar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (CarList.listForCarService.isEmpty()) {
                System.out.println("No available cars found.");
            } else {
                CarService availableCarsToRemove = new CarService();
                availableCarsToRemove.showAvailableCars();

                System.out.println("Enter car ID to remove: ");
                int carID = scanner.nextInt();

                Query<Car> query = session.createNativeQuery("DELETE FROM car WHERE id = :id", Car.class);
                query.setParameter("id", carID);
                System.out.println("Car with ID " + carID + " has been deleted.");
                CarList.queryList = query.getResultList();
                CarList.listForCarService = CarList.queryList;
                System.out.println(query.getResultList());
            }
        } catch (Exception removeCarException) {
            removeCarException.printStackTrace();
        }
    }
}

