package be.springboot.pp.ecommerce;

public interface PaymentProcessor {
    boolean processPayment();
    double getPayableAmount();
}
