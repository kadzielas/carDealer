package com.car.dealer.facade;

import com.car.dealer.service.CarService;

import java.util.Scanner;

public class homeFacade {
    public static void homePage() {
        final CarService carService = new CarService();
        carService.getAllCarsFromDataBase();
        Scanner scanner = new Scanner(System.in);


        int menu;
        do {
            try {
                System.out.print("""
                        ------------------------------
                        Home page
                        \n1.Buy car
                        2.Add new car
                        3.Edit car
                        4.Remove car
                        0.Exit
                        ------------------------------
                        \nWhere are we going?:\s""");

                menu = scanner.nextInt();

                switch (menu) {
                    case 1:
                        carsFacade.availableCars();
                        break;
                    case 2:
                        carService.validateCar();
                        break;
                    case 3:
                        carService.editCar();
                        break;
                    case 4:
                        carService.removeCar();
                        break;
                    case 0:
                        System.out.println("\nSee you later!");
                }
            }catch (Exception menuException){
                System.out.println("Wrong number. You are back to previous page.\n");
                menu = 0;
            }
        } while (menu != 0);
        scanner.close();

    }
}
