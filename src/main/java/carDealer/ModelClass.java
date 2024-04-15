package carDealer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@RequiredArgsConstructor
public class ModelClass {

    private String manufacturer;
    private String model;
    public float engine;
    public BigDecimal engine2;
    public int price;
    
}
