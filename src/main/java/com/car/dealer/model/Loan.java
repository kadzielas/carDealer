package com.car.dealer.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Loan {
    private final BigDecimal year = new BigDecimal("12");
    private final BigDecimal percent = new BigDecimal("0.15");
    private final String loanTime[] = {"null", "One year price: ", "Two years price: ", "Three years price: "};

    //TODO Sebastian źle pojmujesz klasy modelowe. Klasa modelowa to taka klasa, która nie przyjmuje wartości z góry narzuconych
    // Tutaj jeszcze mógłbym się zgodzić z polem year jako 12, ale prędzej jako int, bo nie przewidujesz zeby rok mial 11.999999 wartości
    //Klasa modelowa to taka, która ma mieć tylko i wyłącznie pola, do późniejszego użytku, popatrz na klase Car, która
    // powinna być dla Ciebie wzorem, dodatkowo brakuje mi tutakich pól jak yearCost, yearPercentPrice, totalYearPrice
    // one powinny być tutaj zainicjalizowane a w CarService inwidualnie do każdego przypadku settowane, tak przynajmniej nakazuje logika
    //dodatkowa metoda już powinna coś zwracać tutaj nasz obiekt Loan

}
