package com.car.dealer;

import com.car.dealer.facade.CarFacade;
import com.car.dealer.model.Car;
import com.car.dealer.service.CarService;

import java.util.Scanner;


public class Demo {

    public static void main(String[] args) {
        CarService.loadApplicationFile();
        Scanner scanner = new Scanner(System.in);
        int menu;
        do {
            System.out.println("\nMAIN MENU\n");
            System.out.println("1.Buy car");
            System.out.println("2.Add new car");
            System.out.println("3.Edit car");
            System.out.println("4.Remove car");
            System.out.println("0.Exit\n");

            System.out.print("Where are we going?: ");
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    CarFacade.CarList();
                    break;
                case 2:
                    CarService.validateCar(new Car());
                    break;
                case 3:
                    CarService.editCar(new Car());
                    break;
                case 0:
                    System.out.println("\nSee you later!");
            }
        } while (menu != 0);
        scanner.close();


    }
}
