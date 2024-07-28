package com.car.dealer;

import com.car.dealer.facade.CarFacade;
import com.car.dealer.service.CarService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        final CarService connectToDataBase = new CarService();
        connectToDataBase.getAllCarsFromDataBase();
        Scanner scanner = new Scanner(System.in);
        int menu;
        do {
            System.out.println("""
                    Home page
                    \n1.Buy car
                    2.Add new car
                    3.Edit car
                    4.Remove car
                    0.Exit
                    \nWhere are we going?:""");

            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    CarFacade.CarList();
                    break;
                case 2:
                    final CarService validator = new CarService();
                    validator.validateCar();
                    break;
                case 3:
                   final CarService editor = new CarService();
                    editor.editCar();
                    break;
                case 4:
                    final CarService remover = new CarService();
                    remover.removeCar();
                    break;
                case 0:
                    System.out.println("\nSee you later!");
            }
        } while (menu != 0);
        scanner.close();
    }
}
