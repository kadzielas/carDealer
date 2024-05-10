package com.car.dealer.validator;

import com.car.dealer.common.Currency;
import com.car.dealer.common.Fuel;
import com.car.dealer.common.Manufacturer;
import com.car.dealer.common.Model;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CarValidator {

    public Model validateModel() {
        Model model;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                String modelInput = scanner.nextLine().toUpperCase();
                model = Model.valueOf(modelInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Model example: SERIES2, ACLASS, SUPERDUTY, ZAFIRA");
                System.out.println("Please provide correct model: ");
            }
        } while (true);
        return model;
    }

    public Manufacturer validateManufacturer() {
        Manufacturer manufacturer;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                String manufacturerInput = scanner.nextLine().toUpperCase();
                manufacturer = Manufacturer.valueOf(manufacturerInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Manufacturer example: OPEL, AUDI, MERCEDES, BMW");
                System.out.println("Please provide correct manufacturer: ");
            }
        } while (true);
        return manufacturer;
    }


    public Fuel validateFuel() {
        Fuel fuel;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                String fuelInput = scanner.nextLine().toUpperCase();
                fuel = Fuel.valueOf(fuelInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Fuel example: PB, LPG, DIESEL");
                System.out.println("Please provide correct type of fuel: ");
            }
        } while (true);
        return fuel;
    }

    public Currency validateCurrency() {
        Currency currency;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                String currencyInput = scanner.nextLine().toUpperCase();
                currency = Currency.valueOf(currencyInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Currency example: PLN, EUR, USD, GBP");
                System.out.println("Please provide correct currency: ");
            }
        } while (true);
        return currency;
    }

    public BigDecimal validatePrice(BigDecimal price) {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                BigDecimal priceInput = scanner.nextBigDecimal();
                if (isBiggerThanZero(priceInput)) price = priceInput;
                break;
            } catch (InputMismatchException e) {
                System.out.println("Price example: 3000.00, 5200.50");
                System.out.println("Please provide correct value of price:");
                scanner.next();
            }
        } while (true);
        return price;
    }

    public BigDecimal validateEngine(BigDecimal engine) {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                BigDecimal engineInput = scanner.nextBigDecimal();
                if (isBiggerThanZero(engineInput)) engine = engineInput;
                break;

            } catch (InputMismatchException e) {
                System.out.println("Engine example: 3.0, 2.5, 1.4");
                System.out.println("Please provide correct value of engine:");
                scanner.next();
            }
        } while (true);
        return engine;
    }

    private Boolean isBiggerThanZero(BigDecimal firstValue) {

        int comparisonValue = firstValue.compareTo(BigDecimal.ZERO);
        if (comparisonValue > 0) {
            return true;
        } else if (comparisonValue < 0) {
            return false;
        } else {
            return false;
        }
    }
}
