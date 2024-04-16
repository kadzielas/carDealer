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
    public BigDecimal engine2;
    public BigDecimal price2;
    public Currency currency;
    
}
