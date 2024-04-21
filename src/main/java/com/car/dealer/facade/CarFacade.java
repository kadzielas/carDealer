package com.car.dealer.facade;

import com.car.dealer.service.CarService;
import com.car.dealer.common.Currency;
import com.car.dealer.service.CarBuilder;

import java.math.BigDecimal;
import java.util.Scanner;
public class CarFacade extends CarService {
    public static void CarList(){
        Scanner scanner = new Scanner(System.in);
        int menu;

        CarBuilder test1 = new CarBuilder();
        test1.setID(12);
        test1.setManufacturer("Audi");
        test1.setModel("a7");
        test1.setPrice(new BigDecimal("96000.00"));
        test1.setEngine(new BigDecimal("3.5"));
        test1.setCurrency(Currency.PLN);
        test1.addCar(test1);

        CarBuilder test2 = new CarBuilder();
        test2.setID(5);
        test2.setManufacturer("Seat");
        test2.setModel("Ibiza");
        test2.setPrice(new BigDecimal("8000.00"));
        test2.setEngine(new BigDecimal("1.6"));
        test2.setCurrency(Currency.EUR);
        test2.addCar(test2);

        CarBuilder test3 = new CarBuilder();
        test3.setID(16);
        test3.setManufacturer("Skoda");
        test3.setModel("Fabia");
        test3.setPrice(new BigDecimal("7000.00"));
        test3.setEngine(new BigDecimal("1.4"));
        test3.setCurrency(Currency.USD);
        test3.addCar(test3);

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
                    showAllCars();
                    System.out.println("\n");
                    break;

                case 2:
                    findByManufacturer();
                    System.out.println("\n");
                    break;

                case 3:
                    showLoanPrediction();
                    System.out.println("\n");
                    break;

                case 4:
            }
        } while (menu != 4);
    }

    //todo dodaj metode ktora zaladuje ci dane testowe, gdzie uwazasz ze to be sluszne
}
