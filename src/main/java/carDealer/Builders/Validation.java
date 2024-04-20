package carDealer.Builders;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

public class Validation extends CarBuilder {

    public Predicate<BigDecimal> TESTVALID(BigDecimal price2){
        BigDecimal price = new BigDecimal("0");
        Predicate<BigDecimal> carPredicate = car -> price2.equals(price);
        if(!price2.equals(price)){
            return carPredicate;
        } else {
            System.out.println("False");
        }
        return carPredicate;
    }
    public Currency validationCurrency(){

        do {
            Scanner scanner = new Scanner(System.in);
            try {
                String currencyInput = scanner.nextLine().toUpperCase();
                currency = Currency.valueOf(currencyInput);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("Please provide correct currency: ");
                continue;
            }

        }while (true);
        return currency;
    }

    public void validationPrice(){
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                price2 = scanner.nextBigDecimal();
                if (isBiggerThanZero(price2)) {
                    TESTVALID(price2);
                    break;
                }
            }catch (InputMismatchException e){
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
                engine2 = scanner.nextBigDecimal();
                if (isBiggerThanZero(engine2)) {
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
        if ( comparisonValue > 0) {
            return  true;
        } else if (comparisonValue < 0) {
            return false;
        } else {
            return false;
        }
    }
}
