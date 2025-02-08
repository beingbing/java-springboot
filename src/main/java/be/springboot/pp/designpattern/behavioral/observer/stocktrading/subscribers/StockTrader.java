package be.springboot.pp.designpattern.behavioral.observer.stocktrading.subscribers;

import be.springboot.pp.designpattern.behavioral.observer.stocktrading.dtos.StockValue;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.enums.StockName;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.publishers.StockPublisher;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
public class StockTrader implements StockSubscriber {
    private final String name;
    private final List<StockPublisher> publishers;
    private final Map<StockName, StockValue> stockValues;

    public StockTrader(String name) {
        this.name = name;
        stockValues = new HashMap<>();
        publishers = new ArrayList<>();
    }

    @Override
    public void update(StockName stock, StockValue value) {
        System.out.println(name + " received " + stock + " price: " + value);

        if (!stockValues.containsKey(stock)
                || stockValues.get(stock).getVersion() < value.getVersion()) {
            stockValues.put(stock, value);
        }
    }
}
