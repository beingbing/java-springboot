package be.springboot.pp.designpattern.behavioral.strategy.paymentprocessor;

import be.springboot.pp.designpattern.behavioral.strategy.paymentprocessor.paymentstrategy.CreditCardPayment;
import be.springboot.pp.designpattern.behavioral.strategy.paymentprocessor.paymentstrategy.GooglePayPayment;
import be.springboot.pp.designpattern.behavioral.strategy.paymentprocessor.paymentstrategy.PayPalPayment;

public class Tester {

    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        // Use Credit Card Payment
        paymentProcessor.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        paymentProcessor.processPayment(150.75);

        // Use PayPal Payment
        paymentProcessor.setPaymentStrategy(new PayPalPayment("user@example.com"));
        paymentProcessor.processPayment(89.50);

        // Use Google Pay Payment
        paymentProcessor.setPaymentStrategy(new GooglePayPayment("+1234567890"));
        paymentProcessor.processPayment(45.25);
    }
}
