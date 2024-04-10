package carDealer;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menu;

        do{
            System.out.println("\nMAIN MENU\n");
            System.out.println("1.List of ready cars");
            System.out.println("2.Add new Audi");
            System.out.println("3.Add new BMW");
            System.out.println("4.Add new Seat");
            System.out.println("5.Exit\n");

            System.out.print("Where are we going?: ");
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    ListofReadyCars.CarList();
                    break;
                case 2:
                    CreateCar.AUDI();
                    break;
                case 3:
                    CreateCar.BMW();
                    break;
                case 4:
                    CreateCar.SEAT();
                    break;
                case 5:
                    System.out.println("\nSee you later!");
            }
        }while (menu != 5);
        scanner.close();
    }
}
