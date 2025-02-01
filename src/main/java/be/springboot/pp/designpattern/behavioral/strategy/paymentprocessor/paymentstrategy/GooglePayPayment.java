package be.springboot.pp.designpattern.behavioral.strategy.paymentprocessor.paymentstrategy;

public class GooglePayPayment implements PaymentStrategy {
    private String phoneNumber;

    public GooglePayPayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Google Pay: " + phoneNumber);
    }
}
