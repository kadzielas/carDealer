package carDealer;

import java.util.InputMismatchException;
import java.util.Scanner;

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

    public void validationEngine(){
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                engine = scanner.nextFloat();
                if (engine >= 0.0f) {
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

    public static void AUDI () {
        CreateCar validator = new CreateCar();
        Scanner scanner = new Scanner(System.in);

        CreateCar car = new CreateCar();
        car.setManufacturer("Audi");
        System.out.print("Provide model: ");
        car.setModel(scanner.next());
        System.out.print("Provide price: ");
        validator.validationPrice();
        car.setPrice(validator.getPrice());
        System.out.print("Provide engine: ");
        validator.validationEngine();
        car.setEngine(validator.getEngine());
        car.addAudi(car);
        System.out.println("\nCar has been created with following deatils: " + "\nModel: " + car.getModel() + " " + "\nEngine: " + car.getEngine() + " " + "\nPrice: " + car.getPrice());
    }
    public static void BMW () {
        CreateCar validator = new CreateCar();
        Scanner scanner = new Scanner(System.in);

        CreateCar car = new CreateCar();
        car.setManufacturer("BMW");
        System.out.print("Provide model: ");
        car.setModel(scanner.next());
        System.out.print("Provide price: ");
        validator.validationPrice();
        car.setPrice(validator.getPrice());
        System.out.print("Provide engine: ");
        validator.validationEngine();
        car.setEngine(validator.getEngine());
        car.addBMW(car);
        System.out.println("\nCar has been created with following deatils: " + "\nModel: " + car.getModel() + " " + "\nEngine: " + car.getEngine() + " " + "\nPrice: " + car.getPrice());
    }
    public static void SEAT () {
        CreateCar validator = new CreateCar();
        Scanner scanner = new Scanner(System.in);

        CreateCar car = new CreateCar();
        car.setManufacturer("Seat");
        System.out.print("Provide model: ");
        car.setModel(scanner.next());
        System.out.print("Provide price: ");
        validator.validationPrice();
        car.setPrice(validator.getPrice());
        System.out.print("Provide engine: ");
        validator.validationEngine();
        car.setEngine(validator.getEngine());
        car.addSeat(car);
        System.out.println("\nCar has been created with following deatils: " + "\nModel: " + car.getModel() + " " + "\nEngine: " + car.getEngine() + " " + "\nPrice: " + car.getPrice());
    }

}
