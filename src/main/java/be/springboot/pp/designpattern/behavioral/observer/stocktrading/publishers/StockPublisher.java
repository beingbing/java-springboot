package be.springboot.pp.designpattern.behavioral.observer.stocktrading.publishers;

import be.springboot.pp.designpattern.behavioral.observer.stocktrading.dtos.StockValue;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.enums.StockName;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.subscribers.StockSubscriber;

public interface StockPublisher {
    boolean subscribe(StockName stock, StockSubscriber subscriber);
    boolean unsubscribe(StockName stock, StockSubscriber subscriber);
    void publishNews(StockName stock, StockValue value);
}
