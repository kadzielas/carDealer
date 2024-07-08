package com.car.dealer.facade;

import com.car.dealer.service.CarService;

import java.util.Scanner;
public class CarFacade {
    public static void CarList() {
        Scanner scanner = new Scanner(System.in);
        int menu = 0;

        do {
            try {

                System.out.println("""
                        1.Show list of available cars
                        2.Find cars by manufacturer
                        3.Check price on loan
                        0.Home page
                        \nWhere are we going?:""");
                menu = scanner.nextByte();
                System.out.println("\n");

                switch (menu) {

                    case 1:
                        CarService cars = new CarService();
                        cars.showAllCars();
                        System.out.println("\n");
                        break;

                    case 2:
                        CarService manufacturerResult = new CarService();
                        manufacturerResult.findCarByManufacturer();
                        System.out.println("\n");
                        break;

                    case 3:
                        CarService carToPrediction = new CarService();
                        carToPrediction.selectCarToPrediction();
                        System.out.println("\n");
                        break;

                    case 0:
                }

            } catch (Exception exception) {
                System.out.println("Wrong number. You are back to previous page.\n");
                exception.printStackTrace();

            }
        } while (menu != 0);
    }
}

