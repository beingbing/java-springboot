package be.springboot.pp.designpattern.behavioral.observer.stocktrading.publishers;

import be.springboot.pp.designpattern.behavioral.observer.stocktrading.dtos.StockValue;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.enums.StockName;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.subscribers.StockSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockExchange implements StockPublisher  {
    private final String name;
    private final Map<StockName, List<StockSubscriber>> stockSubscribers;

    public StockExchange(String name) {
        this.name = name;
        this.stockSubscribers = new HashMap<>();
    }

    @Override
    public boolean subscribe(StockName stock, StockSubscriber subscriber) {
        System.out.println(subscriber + " subscribed to " + stock);
        return stockSubscribers.computeIfAbsent(stock, k -> new ArrayList<>()).add(subscriber);
    }

    @Override
    public boolean unsubscribe(StockName stock, StockSubscriber subscriber) {
        System.out.println(subscriber + " unsubscribed from " + stock);
        if (!stockSubscribers.containsKey(stock)) return false;
        return stockSubscribers.get(stock).remove(subscriber);
    }

    @Override
    public void publishNews(StockName stock, StockValue value) {
        System.out.println("\n📰 Publishing " + stock + " Price: " + value);
        if (stockSubscribers.containsKey(stock))
            for (StockSubscriber subscriber : stockSubscribers.get(stock))
                subscriber.update(stock, value);
    }
}
