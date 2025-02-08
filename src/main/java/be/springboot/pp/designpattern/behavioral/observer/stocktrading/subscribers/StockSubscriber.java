package be.springboot.pp.designpattern.behavioral.observer.stocktrading.subscribers;

import be.springboot.pp.designpattern.behavioral.observer.stocktrading.dtos.StockValue;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.enums.StockName;

public interface StockSubscriber {
    void update(StockName stock, StockValue value);
}
