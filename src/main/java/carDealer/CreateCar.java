package carDealer;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

public class CreateCar extends ListOfCars {
    public void validationPrice(){
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                price = scanner.nextInt();
                if (price >= 0) {
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

        public Predicate<CreateCar> TESTVALID(int price){
            Predicate<CreateCar> carPredicate = car -> car.getPrice() < price;
            Predicate<CreateCar> carPredicate2 = car -> car.getPrice() < price;
            return carPredicate.and(carPredicate2);
    }

    public static void Car(){
        CreateCar validator = new CreateCar();
        Scanner scanner = new Scanner(System.in);

        CreateCar car = new CreateCar();
        System.out.println("Provide manufacturer: ");
        car.setManufacturer(scanner.next());
        System.out.print("Provide model: ");
        car.setModel(scanner.next());
        System.out.print("Provide price: ");
        validator.validationPrice();
        car.setPrice(validator.getPrice());
        System.out.print("Provide engine: ");
        validator.validationEngine();
        car.setEngine(validator.getEngine());
        car.addCar(car);
        System.out.println("\nCar has been created with following details: " + "\nModel: " + car.getModel() + " " + "\nEngine: " + car.getEngine() + " " + "\nPrice: " + car.getPrice());

    }
}
