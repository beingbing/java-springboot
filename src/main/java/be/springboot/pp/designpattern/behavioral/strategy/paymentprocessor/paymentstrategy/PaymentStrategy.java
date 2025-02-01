package be.springboot.pp.designpattern.behavioral.strategy.paymentprocessor.paymentstrategy;

public interface PaymentStrategy {
    void pay(double amount);
}
