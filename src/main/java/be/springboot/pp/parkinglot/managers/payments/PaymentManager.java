package be.springboot.pp.parkinglot.managers.payments;

import be.springboot.pp.parkinglot.dtos.PaymentDetails;

public interface PaymentManager {
    boolean executePayment(PaymentDetails paymentDetails);
}
