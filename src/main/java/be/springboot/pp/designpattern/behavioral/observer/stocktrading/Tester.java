package be.springboot.pp.designpattern.behavioral.observer.stocktrading;

import be.springboot.pp.designpattern.behavioral.observer.stocktrading.dtos.StockValue;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.enums.Currency;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.enums.StockName;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.publishers.StockExchange;
import be.springboot.pp.designpattern.behavioral.observer.stocktrading.subscribers.StockTrader;

import java.util.EnumSet;

public class Tester {

    public static void main(String[] args) {
        StockExchange bse = new StockExchange("BSE");
        StockExchange nse = new StockExchange("NSE");

        StockTrader alice = new StockTrader("alice");
        StockTrader bob = new StockTrader("bob");
        StockTrader charlie = new StockTrader("charlie");

        EnumSet.of(StockName.APPLE, StockName.GOOGLE, StockName.AMAZON).forEach(stock -> bse.subscribe(stock, alice));
        EnumSet.of(StockName.NETFLIX, StockName.TESLA, StockName.AMAZON).forEach(stock -> nse.subscribe(stock, alice));
        EnumSet.of(StockName.APPLE, StockName.GOOGLE, StockName.AMAZON).forEach(stock -> bse.subscribe(stock, bob));
        EnumSet.of(StockName.APPLE, StockName.GOOGLE, StockName.AMAZON).forEach(stock -> bse.subscribe(stock, bob));
        EnumSet.of(StockName.NETFLIX, StockName.GOOGLE, StockName.TESLA).forEach(stock -> bse.subscribe(stock, charlie));
        EnumSet.of(StockName.NETFLIX, StockName.GOOGLE, StockName.TESLA).forEach(stock -> bse.subscribe(stock, charlie));

        bse.publishNews(StockName.GOOGLE, new StockValue(1, 10, Currency.INR));
        bse.publishNews(StockName.NETFLIX, new StockValue(1, 5, Currency.INR));

        nse.publishNews(StockName.GOOGLE, new StockValue(1, 12, Currency.INR));
        nse.publishNews(StockName.APPLE, new StockValue(1, 20, Currency.INR));

        bse.publishNews(StockName.GOOGLE, new StockValue(2, 12, Currency.INR));

        bse.unsubscribe(StockName.GOOGLE, bob);

        bse.publishNews(StockName.GOOGLE, new StockValue(3, 15, Currency.INR));
    }
}
