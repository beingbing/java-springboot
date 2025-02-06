package be.springboot.pp.parkinglot.managers.payments;

import be.springboot.pp.parkinglot.enums.PaymentMode;
import be.springboot.pp.parkinglot.managers.payments.impl.CardPayment;
import be.springboot.pp.parkinglot.managers.payments.impl.CashPayment;
import be.springboot.pp.parkinglot.managers.payments.impl.UpiPayment;

public class PaymentFactory {

    private PaymentFactory() {}

    public static PaymentManager getPaymentMethod(PaymentMode paymentMode) {
        return switch (paymentMode) {
            case CARD -> new CardPayment();
            case CASH -> new CashPayment();
            case UPI -> new UpiPayment();
        };
    }
}
