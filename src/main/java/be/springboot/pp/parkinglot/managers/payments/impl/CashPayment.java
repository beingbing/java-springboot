package be.springboot.pp.parkinglot.managers.payments.impl;

import be.springboot.pp.parkinglot.dtos.PaymentDetails;
import be.springboot.pp.parkinglot.managers.payments.PaymentManager;

public class CashPayment implements PaymentManager {
    @Override
    public boolean executePayment(PaymentDetails paymentDetails) {
        return false;
    }
}
