package be.springboot.pp.designpattern.behavioral.observer.stocktrading.dtos;

import be.springboot.pp.designpattern.behavioral.observer.stocktrading.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class StockValue {
    private final int version;
    private final double amount;
    private final Currency currency;
}
