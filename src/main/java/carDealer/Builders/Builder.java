package carDealer.Builders;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
@Getter
@Setter
@RequiredArgsConstructor
@lombok.Builder

public class Builder {
    private Integer ID;
    public static AtomicInteger nextId = new AtomicInteger();
    private String manufacturer;
    private String model;
    public BigDecimal engine2;
    public BigDecimal price2;
    public Currency currency;
    
}
