package carDealer;

import java.util.Scanner;

public class ListOfReadyCars extends ListOfCars{
    public static void CarList(){
        Scanner scanner = new Scanner(System.in);
        int menu;

        CreateCar test1 = new CreateCar();
        test1.setManufacturer("Audi");
        test1.setModel("a10");
        test1.setPrice(20000);
        test1.setEngine(2.0f);
        test1.addCar(test1);

        CreateCar test2 = new CreateCar();
        test2.setManufacturer("Seat");
        test2.setModel("Ibiza");
        test2.setPrice(696969);
        test2.setEngine(1.4f);
        test2.addCar(test2);

        CreateCar test3 = new CreateCar();
        test3.setManufacturer("Skoda");
        test3.setModel("Fabia");
        test3.setPrice(15000);
        test3.setEngine(5.0f);
        test3.addCar(test3);

        do {
            System.out.println("1.Every available car");
            System.out.println("2.Filter by manufacturer");
            System.out.println("3.Soon");
            System.out.println("4.Home page\n");
            System.out.print("Where are we going?: ");
            menu = scanner.nextByte();
            System.out.println("\n");

            switch (menu){

                case 1:
                    carDealer.ListOfCars.showList();
                    System.out.println("\n");
                    break;

                case 2:
                    carDealer.ListOfCars.carStream();
                    System.out.println("\n");
                    break;

                case 3:
                    System.out.println("\n");
                    break;

                case 4:
            }
        } while (menu != 4);
    }
}
