package be.springboot.pp.fooddeliverysystem.managers.payment;

import be.springboot.pp.fooddeliverysystem.dtos.PaymentResponse;

public interface PaymentManager {

    PaymentResponse executePayment();
}
