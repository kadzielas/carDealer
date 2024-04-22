package com.car.dealer.facade;

import com.car.dealer.service.CarService;
import java.util.Scanner;
public class CarFacade {
    public static void CarList(){
        Scanner scanner = new Scanner(System.in);
        int menu;

        do {
            System.out.println("1.Show list of available cars");
            System.out.println("2.Find cars by manufacturer");
            System.out.println("3.Check price on loan");
            System.out.println("4.Home page\n");
            System.out.print("Where are we going?: ");
            menu = scanner.nextByte();
            System.out.println("\n");

            switch (menu){

                case 1:
                    CarService.showAllCars();
                    System.out.println("\n");
                    break;

                case 2:
                    CarService.findByManufacturer();
                    System.out.println("\n");
                    break;

                case 3:
                    CarService.showLoanPrediction();
                    System.out.println("\n");
                    break;

                case 4:
            }
        } while (menu != 4);
    }

    //todo dodaj metode ktora zaladuje ci dane testowe, gdzie uwazasz ze to be sluszne
    //todo???
}
