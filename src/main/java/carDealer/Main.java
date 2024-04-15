package carDealer;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menu;

        do{
            System.out.println("\nMAIN MENU\n");
            System.out.println("1.List of ready cars");
            System.out.println("2.Add new car");
            System.out.println("3.Exit\n");

            System.out.print("Where are we going?: ");
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    ListOfReadyCars.CarList();
                    break;
                case 2:
                    CreateCar.Car();
                    break;
                case 3:
                    System.out.println("\nSee you later!");
            }
        }while (menu != 5);
        scanner.close();
    }
}
