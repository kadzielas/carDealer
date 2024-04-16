package carDealer;

import lombok.Getter;

import java.util.*;

@Getter
public class ListOfCars extends ModelClass {


        private  static List<CreateCar> ListOfCars = new ArrayList<>();
        public List<CreateCar> getListOfCars() {
            return ListOfCars;
        }


        public void addCar(CreateCar createdCar){
            CreateCar car = new CreateCar();
            ListOfCars.add(createdCar);
        }

        public static void carStream(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Provide manufacturer name: ");
            String filterManufacturer = scanner.next();
            ListOfCars.stream()
                    .filter(car -> car.getManufacturer().equalsIgnoreCase(filterManufacturer))
                    .forEach(car -> System.out.println("Model: " + car.getModel() + " " + "Engine: " + car.getEngine2() + " " + "Price: " + car.getPrice2() + " " + car.currency));
        }

        public static void showList(){
//            ListOfCars car = new ListOfCars();
//            car.getListOfCars().forEach(
//                    createcar ->
//                            System.out.println("Manufacturer: " + createcar.getManufacturer() + "\nModel: " + createcar.getModel() + "\nEngine: " + createcar.getEngine2() + "\nPrice: " + createcar.getPrice() + "\n")
//            );
            ListOfCars.stream()
                    .forEach(car -> System.out.println("Manufacturer: " + car.getManufacturer() + " | " + "Model: " + car.getModel() + " | " + "Engine: " + car.getEngine2() + " | " + "Price: " + car.getPrice2() + " " + car.currency));

        }




}
