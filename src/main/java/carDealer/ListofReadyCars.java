package carDealer;

import lombok.ToString;

import java.util.Scanner;

public class ListofReadyCars extends ListOfCars{
    public static void CarList(){
        Scanner scanner = new Scanner(System.in);
        int menu;
        System.out.println("\nPlease choose manufacturer for car list\n");

        do {
            System.out.println("1.Audi");
            System.out.println("2.BMW");
            System.out.println("3.Skoda");
            System.out.println("4.Home page\n");
            System.out.print("Where are we going?: ");
            menu = scanner.nextByte();
            System.out.println("\n");

            switch (menu){

                case 1:
                    ListOfCars carAudi = new ListOfCars();
                    carAudi.getListOfAudi().forEach(
                            createcar ->
                                    System.out.println("Model: " + createcar.getModel() + " " + "Engine: " + createcar.getEngine() + " " + "Price: " + createcar.getPrice())
                    );
                    System.out.println("\n");
                    break;

                case 2:
                    ListOfCars carBMW = new ListOfCars();
                    carBMW.getListOfBMW().forEach(
                            createcar ->
                                    System.out.println("Model: " + createcar.getModel() + " " + "Engine: " + createcar.getEngine() + " " + "Price: " + createcar.getPrice())
                    );
                    System.out.println("\n");
                    break;

                case 3:
                    ListOfCars carSeat = new ListOfCars();
                    carSeat.getListOfSeat().forEach(
                            createcar ->
                                    System.out.println("Model: " + createcar.getModel() + " " + "Engine: " + createcar.getEngine() + " " + "Price: " + createcar.getPrice())
                    );
                    System.out.println("\n");
                    break;

                case 4:
            }
        } while (menu != 4);
    }
}
