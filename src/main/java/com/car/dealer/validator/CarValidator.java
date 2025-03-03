package com.car.dealer.validator;

import com.car.dealer.common.Currency;
import com.car.dealer.common.Fuel;
import com.car.dealer.common.Manufacturer;
import com.car.dealer.common.Model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CarValidator {
   final private Scanner scanner = new Scanner(System.in);

    public Model validateModel() {
        Model model;
        do {
            try {
                String modelInput = scanner.nextLine().toUpperCase();
                model = Model.valueOf(modelInput);
                break;
            } catch (IllegalArgumentException modelException) {
                System.out.println("Model example: SERIES2, ACLASS, SUPERDUTY, ZAFIRA");
                System.out.println("Please provide correct model: ");
            }
        } while (true);
        return model;
    }

    public Manufacturer validateManufacturer() {
        Manufacturer manufacturer;
        do {
            try {
                String manufacturerInput = scanner.nextLine().toUpperCase();
                manufacturer = Manufacturer.valueOf(manufacturerInput);

                break;
            } catch (IllegalArgumentException manufacturerException) {
                System.out.println("Manufacturer example: OPEL, AUDI, MERCEDES, BMW");
                System.out.println("Please provide correct manufacturer: ");
            }
        } while (true);
        return manufacturer;
    }


    public Fuel validateFuel() {
        Fuel fuel;
        do {
            try {
                String fuelInput = scanner.nextLine().toUpperCase();
                fuel = Fuel.valueOf(fuelInput);
                break;
            } catch (IllegalArgumentException fuelException) {
                System.out.println("Fuel example: PB, LPG, DIESEL or HYBRID");
                System.out.println("Please provide correct type of fuel: ");
            }
        } while (true);
        return fuel;
    }

    public Currency validateCurrency() {
        Currency currency;
        do {
            try {
                String currencyInput = scanner.nextLine().toUpperCase();
                currency = Currency.valueOf(currencyInput);

                break;
            } catch (IllegalArgumentException currencyException) {
                System.out.println("Currency example: PLN, EUR, USD, GBP");
                System.out.println("Please provide correct currency: ");
            }
        } while (true);
        return currency;
    }

    public int validateYear() {
        int year = 0;
        int currentYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
        boolean test = true;

        do {
            try {
                int yearInput = scanner.nextInt();
                if (yearInput > 1950 && yearInput < (currentYear + 1)){
                    year = yearInput;
                    test = false;
                }
                else {
                    System.out.println("Year must be between 1950 and " + (currentYear + 1));
                }
            } catch (InputMismatchException yearException) {
                System.out.println("Year example: 1990, 2010, 2024");
                System.out.println("Please provide correct year of car: ");
                scanner.nextInt();
            }
        } while (test);
        return year;
    }

    public BigDecimal validatePrice(BigDecimal price) {
        do {
            try {
                BigDecimal priceInput = scanner.nextBigDecimal();
                if (isBiggerThanZero(priceInput)) price = priceInput;
                break;
            } catch (Exception priceException) {
                System.out.println("Price example: 3000.00, 5200.50");
                System.out.println("Please provide correct price value: ");
                scanner.next();
            }
        } while (true);
        return price;
    }

    public BigDecimal validateEngine(BigDecimal engine) {
        do {
            try {
                BigDecimal engineInput = scanner.nextBigDecimal();
                if (isBiggerThanZero(engineInput)) engine = engineInput;
                break;

            } catch (Exception engineException) {
                System.out.println("Engine example: 3.0, 2.5, 1.4");
                System.out.println("Please provide correct engine:");
                scanner.next();
            }
        } while (true);
        return engine;
    }

    private Boolean isBiggerThanZero(BigDecimal valueToCheck) {

        int comparisonValue = valueToCheck.compareTo(BigDecimal.ZERO);
        if (comparisonValue > 0) {
            return true;
        } else if (comparisonValue < 0) {
            return false;
        } else {
            return false;
        }
    }
}
