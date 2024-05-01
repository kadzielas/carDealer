package com.car.dealer;

import com.car.dealer.facade.CarFacade;
import com.car.dealer.service.CarService;

import java.util.Scanner;


public class Demo {

    public static void main(String[] args) {
        CarService.loadApplicationFile();
        Scanner scanner = new Scanner(System.in);
        int menu;
        do {
            System.out.println("\nMAIN MENU\n" + "1.Buy car\n" + "2.Add new car\n" + "3.Edit car\n" +
                    "4.Remove car\n" + "0.Exit\n" + "\nWhere are we going?: ");

            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    CarFacade.CarList();
                    break;
                case 2:
                    CarService validator = new CarService();
                    validator.validateCar();
                    break;
                case 3:
                    CarService editor = new CarService();
                    editor.editCar();
                    break;
                case 4:
                    CarService remover = new CarService();
                    remover.removeCar();
                    break;
                case 0:
                    System.out.println("\nSee you later!");
            }
        } while (menu != 0);
        scanner.close();

    }
}
