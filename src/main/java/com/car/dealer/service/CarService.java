package com.car.dealer.service;

import com.car.dealer.model.Car;
import com.car.dealer.common.Manufacturer;
import com.car.dealer.validator.CarValidator;

import java.io.*; //todo staraj sie nie importowac calych bibliotek tylko te klasy ktore potrzebuejsz
// skonfiguruj sobie intelija tak zeby importowal ci tylko te klasy ktore potrzebujesz a nie cale biblioteki ;)
import java.math.*;
import java.util.*;

import static com.car.dealer.model.CarList.listOfCars;


public class CarService extends Car {
    public static Car validateCar(Car car) { // nie uzywany argument w metodzie, po co to?
        Car carValidate = new Car();
        Scanner scanner = new Scanner(System.in);
        CarValidator validator = new CarValidator();

        System.out.println("Provide manufacturer: ");
        carValidate.setManufacturer(validator.validateManufacturer(carValidate.getManufacturer()));
        System.out.print("Provide model: ");
        carValidate.setModel(scanner.next());
        System.out.print("Provide engine: ");
        carValidate.setEngine(validator.validateEngine(carValidate.getEngine()));
        System.out.println("Provide type of fuel: ");
        carValidate.setFuel(validator.validateFuel(carValidate.getFuel()));
        System.out.print("Provide price: ");
        carValidate.setPrice(validator.validatePrice(carValidate.getPrice()));
        System.out.println("Provide currency: ");
        carValidate.setCurrency(validator.validateCurrency(carValidate.getCurrency()));
        return createCar(carValidate);
    }

    public static Car createCar(Car car) {
        loadApplicationFile(listOfCars);
        int i = loadLastId() + 1;
        Car newCar = Car.builder()//todo co to jest i
                .ID(i)
                .manufacturer(car.getManufacturer())
                .model(car.getModel())
                .engine(car.getEngine())
                .fuel(car.getFuel())
                .price(car.getPrice())
                .currency(car.getCurrency())
                .build();
        listOfCars.add(newCar);

        System.out.println("\nCar has been created with following details: " + "\nModel: " + car.getModel()
                + " " + "\nEngine: " + car.getEngine() + " " + car.getFuel() + " " + "\nPrice: " + car.getPrice() + " "
                + car.getCurrency());

        saveApplicationFile(listOfCars); //todo czemu oddzielnie zapisujesz id i samochody
        saveLastId(i);
        return newCar;
    }

    private static int loadLastId() {
        int lastId = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream("lastId.data");//todo jak to ci dziala, jak plik ma zla nazwe
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
            FileOutputStream fileOutputStream = new FileOutputStream("lastId.data");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(lastId);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private static HashSet<Car> saveApplicationFile(HashSet<Car> car) {//todo nie uzywasz argumentow w metodzie
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("carDealer.data");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Car x : listOfCars) {
                objectOutputStream.writeObject(x);
            }
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException ioe) {
            System.err.println("Error saving to file");
        }
        return loadApplicationFile(listOfCars);
    }

    public static HashSet<Car> loadApplicationFile(HashSet<Car> list) {
        List<Car> loadedCar = null;//todo loaded to moge byc ja a nie ta zmienna, ona jest nie uzywana
        try {
            FileInputStream fileInputStream = new FileInputStream("carDealer.data");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                Car car = (Car) objectInputStream.readObject();
                listOfCars.add(car);
            }
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException |
                 NullPointerException ioe) { //todo lepiej uzyc globalnego Exception niz kazdy z góry wyjatek definiowac
            System.err.println("Error loading file " + ioe.getMessage());
        }
        return list;
    }

    private static Manufacturer selectedManufacturer() { //todo wpisalem CHUJ i sie wyjebalo, popraw
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide manufacturer name: ");
        String filterManufacturer = scanner.next().toUpperCase();
        return Manufacturer.valueOf(filterManufacturer);
    }

    public static Car findByManufacturer(Car car) {
        //todo tu wciaz niepotrzebnie zwracasz caly obiekt car, a nie modyfikujesz go w zaden sposob, wiec lepiej po prostu zrobic voida, skoro
        // po prostu listuje Ci coś ;)

        //** dlaczego static? chcesz zeby ta metoda ciagle trzymala ci wyszukana wartosc?
        Manufacturer manufacturer = selectedManufacturer();
        for (Car e : listOfCars) { //todo co to jest e? nazwj to ladniej
            if (e.getManufacturer().equals(manufacturer)) {
                System.out.println("ID: " + e.getID() + " " + "Model: " + e.getModel() + " " +
                        "Engine: " + e.getEngine() + " " + e.getFuel() + " " + "Price: " + e.getPrice() + " " + e.getCurrency());
            }
        }
//        listOfCars.stream()        //dlaczego to nie działa? a na foreach juz tak
//                .filter(x -> x.getManufacturer().equals(selectedManufacturer())) //todo zamiast selectedmanufacturer -> manufacturer masz juz wyzej
//                .forEach(e -> System.out.println("ID: " + e.getID() + " " + "Model: " + e.getModel() + " " +
//                        "Engine: " + e.getEngine() + " " + "Price: " + e.getPrice() + " " + e.getCurrency()));
        return car;
    }

    public static Car showAllCars(Car car) {// todo musisz przemyslec kiedy stosować obiekt car a kiedy po prostu voida
        //todo tu wciaz niepotrzebnie zwracasz caly obiekt car, a nie modyfikujesz go w zaden sposob, wiec lepiej po prostu zrobic voida, skoro
        // po prostu listuje Ci coś ;)
        listOfCars.forEach(x -> System.out.println("ID: " + x.getID() + " | " + "Manufacturer: " +
                x.getManufacturer() + " | " + "Model: " + x.getModel() + " | " + "Engine: " + x.getEngine() +
                " " + x.getFuel() + " | " + "Price: " + x.getPrice() + " " + x.getCurrency()));
        //** dlaczego static? chcesz zeby ta metoda ciagle trzymala ci wyszukana wartosc?
        return car;
    }

    public static Car selectCarToPrediction(Car car) {
        //** dlaczego static? chcesz zeby ta metoda ciagle trzymala ci wyszukana wartosc?
        //todo tu wciaz niepotrzebnie zwracasz caly obiekt car, a nie modyfikujesz go w zaden sposob, wiec lepiej po prostu zrobic voida, skoro
        // po prostu listuje Ci coś ;)
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which one you want to take on loan? Please provide ID of car: ");
        int selectedCar = scanner.nextInt();

        Car selectedCarObject = listOfCars.stream()
                .filter(x -> x.getID() == selectedCar)
                .findFirst()
                .orElse(null);//todo jest tu ryzyko zwrócenia nulla, intelij to podswietla, musisz przemyslec strukture tego streama

        if (selectedCarObject != null) {

            System.out.println("Selected car: " + "\nManufacturer: " + selectedCarObject.getManufacturer() +
                    " | " + "Model: " + selectedCarObject.getModel() + " | " + "Engine: " +
                    selectedCarObject.getEngine() + " " + car.getFuel() + " | " + "Price: " + //todo car fuel?, nie powinno byc selectedCarObject?
                    selectedCarObject.getPrice() + " " + selectedCarObject.getCurrency() + "\n");
        }
        return checkLoanPrice(selectedCarObject);//tutaj powinno ci podswietlic ze obiekt moze byc nullem, trzeba sie przed tym zabezpieczyc

    }

    private static Car checkLoanPrice(Car car) {//tutaj moim zdaniem lepiej byloby miec oddzielny obiekt Loan, z tymi zmiennymi, niz wolac caly obiekt car
        //wrzuciłbym samo car.getPrice(),zamiast ladowac caly obiekt niepotrzebnie, optymalizacja kochanie i tu tez moze byc void, bo nie zwracasz modyfikowane
        // w zaden sposob obiektu car, wiec wrzucaj tylko potrzebne do metody rzeczy
        BigDecimal year = new BigDecimal("12");
        BigDecimal percent = new BigDecimal("0.15");
        BigDecimal yearCost = car.getPrice().divide(year, 2, BigDecimal.ROUND_UP);
        BigDecimal totalCost = yearCost.multiply(percent);
        BigDecimal totalOneYear = yearCost.add(totalCost).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal totalTwoYeras = totalOneYear.divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal totalThreeYeras = totalOneYear.divide(BigDecimal.valueOf(3), 2, BigDecimal.ROUND_HALF_UP);

        System.out.println("One year loan cost per month: " + totalOneYear + " " + car.getCurrency() +
                "\nTwo years loan cost per month: " + totalTwoYeras + " " + car.getCurrency() +
                "\nThree years loan cost per month: " + totalThreeYeras + " " + car.getCurrency());
        return car;
    }

    public static HashSet<Car> editCar(Car car) { //todo argument nie jest uzywany
        Scanner scanner = new Scanner(System.in);

        CarService.showAllCars(new Car());
        CarValidator validator = new CarValidator();

        int menu;

        System.out.println("Provide ID of car to edit");
        int selectedCar = scanner.nextInt();

        Car selectedCarObject = listOfCars.stream()
                .filter(x -> x.getID() == selectedCar)
                .findFirst()
                .orElse(null);//TODO selectedCarObject moze byc nullem, nie zabepieczyles sie przed tym

        do {
            //todo brzydko ci sie wyswietle menu, 4 i 5 są sklejone popraw
            System.out.println("What you want to change?\n" +
                    "1.Manufacuter\n" + "2.Model\n" + "3.Engine\n" + "4.Fuel" + "5.Price\n" + "6.Currency\n" + "0.Back to home page"); //todo menu w klasie demo inne, tu inne opracuj standard dla siebie!
            menu = scanner.nextInt();
            switch (menu) {
                case 1 -> {
                    System.out.println("Provide new manufacutrer for selected car: ");
                    selectedCarObject.setManufacturer(validator.validateManufacturer(selectedCarObject.getManufacturer()));
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 2 -> {
                    System.out.println("Provide new model for selected car: ");
                    String editModel = scanner.next();
                    selectedCarObject.setModel(editModel);
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
                    selectedCarObject.setFuel(validator.validateFuel(selectedCarObject.getFuel()));
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 5 -> {
                    System.out.println("Provide new price for selected car: ");
                    selectedCarObject.setPrice(validator.validatePrice(selectedCarObject.getPrice()));
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 6 -> {
                    System.out.println("Provide new currency for selected car: ");
                    selectedCarObject.setCurrency(validator.validateCurrency(selectedCarObject.getCurrency()));
                    System.out.println("Change has been saved");
                    System.out.println("\n");
                }
                case 0 -> System.out.println("Back to home page");
            }
        } while (menu != 0);

        return saveApplicationFile(listOfCars);

    }

    public static HashSet<Car> removeCar(Car car) { //todo argument nie jest uzywany!!
        CarService.showAllCars(new Car());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Provide ID of car to remove");
        int selectedCar = scanner.nextInt();

        Iterator<Car> iterator = listOfCars.iterator();
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
        return saveApplicationFile(listOfCars);
    }
}

