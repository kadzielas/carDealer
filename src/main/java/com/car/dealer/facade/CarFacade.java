package com.car.dealer.facade;

import com.car.dealer.model.Car;
import com.car.dealer.service.CarService;
import java.util.Scanner;
public class CarFacade {
    public static void CarList(){
        Scanner scanner = new Scanner(System.in);
        int menu;

        do {
            System.out.println("1.Show list of available cars\n" + "2.Find cars by manufacturer\n" +
                    "3.Check price on loan\n" + "0.Home page\n" + "\nWhere are we going?: ");
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
                    CarService.selectCarToPrediction();
                    System.out.println("\n");
                    break;

                case 0:
            }
        } while (menu != 0);
    }

}
