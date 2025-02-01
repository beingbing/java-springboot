package be.springboot.pp.designpattern.behavioral.strategy.paymentprocessor;

import be.springboot.pp.designpattern.behavioral.strategy.paymentprocessor.paymentstrategy.PaymentStrategy;

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    // Set the strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Execute the payment
    public void processPayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy is not set.");
        }
        paymentStrategy.pay(amount);
    }
}
