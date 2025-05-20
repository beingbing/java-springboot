package be.springboot.pp.ecommerce.payment;

public interface PaymentProcessor {
    boolean processPayment();
    double getPayableAmount();
}
