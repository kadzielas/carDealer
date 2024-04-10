package carDealer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class ListOfCars extends ModelClass {

        private static List<CreateCar> ListOfAudi = new ArrayList<>();
        private static List<CreateCar> ListOfBMW = new ArrayList<>();
        private static List<CreateCar> ListOfSeat = new ArrayList<>();

        public List<CreateCar> getListOfAudi(){
            return ListOfAudi;
        }
        public List<CreateCar> getListOfBMW(){
            return ListOfBMW;
        }
        public List<CreateCar> getListOfSeat(){
            return ListOfSeat;
        }

        public void addAudi(CreateCar createdCar){
            CreateCar car = new CreateCar();
            ListOfAudi.add(createdCar);
        }
        public void addBMW(CreateCar createdCar){
            CreateCar car = new CreateCar();
            ListOfBMW.add(createdCar);
    }
        public void addSeat(CreateCar createdCar){
            CreateCar car = new CreateCar();
            ListOfSeat.add(createdCar);
    }

}
