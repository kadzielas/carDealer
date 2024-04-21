package com.car.dealer.validator;

import com.car.dealer.common.Currency;
import com.car.dealer.service.CarBuilder;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

public class CarValidator {

    public Predicate<BigDecimal> TESTVALID(BigDecimal price2) {
        BigDecimal price = new BigDecimal("0");
        Predicate<BigDecimal> carPredicate = car -> price2.equals(price);
        if (!price2.equals(price)) {
            return carPredicate;
        } else {
            System.out.println("False");
        }
        return carPredicate;
    }

    public Currency validationCurrency(Currency currency) {
        boolean xyz = true;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                String currencyInput = scanner.nextLine().toUpperCase();
                currency = Currency.valueOf(currencyInput);
                xyz = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Please provide correct currency: ");
            }

        } while (xyz);
        return currency;
    }

    public void validationPrice() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                price = scanner.nextBigDecimal();
                if (isBiggerThanZero(price)) {
                    TESTVALID(price);
                    break;
                }
            } catch (InputMismatchException e) {
                e.getStackTrace();
                System.out.println("Please provide correct value:");
                scanner.next();
                continue;
            }
            break;
        } while (true);
    }

    public void validationEngine() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                engine = scanner.nextBigDecimal();
                if (isBiggerThanZero(engine)) {
                    break;
                }
            } catch (InputMismatchException e) {
                e.getStackTrace();
                System.out.println("Please provide correct value:");
                scanner.next();
                continue;
            }
            break;
        } while (true);
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
