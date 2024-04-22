package com.car.dealer;

import com.car.dealer.facade.CarFacade;
import com.car.dealer.service.CarService;
import java.util.Scanner;


public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menu;
        //loadtestddata
        do {
            System.out.println("\nMAIN MENU\n");
            System.out.println("1.Buy car");
            System.out.println("2.Add new car");
            System.out.println("3.Exit\n");

            System.out.print("Where are we going?: ");
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    CarFacade.CarList();
                    break;
                case 2:
                    CarService.addCarForm();
                    break;
                case 3:
                    System.out.println("\nSee you later!");
            }
        } while (menu != 5);
        scanner.close();
    }
}
