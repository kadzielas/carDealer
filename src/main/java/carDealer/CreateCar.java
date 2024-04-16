package carDealer;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateCar extends ListOfCars {
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
        //TODO short if statement
       //TODo poczytaj o bigdecimal
        int comparisonValue = firstValue.compareTo(BigDecimal.ZERO);
        if ( comparisonValue > 0) {
            return  true;
        } else if (comparisonValue < 0) {
            return false;
        } else {
            return false;
        }
   }
//        public Predicate<CreateCar> TESTVALID(int price){
//            Predicate<CreateCar> carPredicate = car -> car.getPrice2() < price2;
//            return carPredicate;
//    }

    public static void Car(){
        CreateCar validator = new CreateCar();
        Scanner scanner = new Scanner(System.in);

        CreateCar car = new CreateCar();
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
