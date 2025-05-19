package be.springboot.pp.fooddeliverysystem.managers.payment;

import java.util.Map;

public class PaymentFactory {

    public static PaymentManager getPaymentManager(String paymentMode, Map<String, String> paymentInfo) {
        PaymentManager paymentManager = null;
        if (paymentMode.equalsIgnoreCase("NetBanking")) {
            paymentManager = NetBankingPayment
                    .builder()
                    .bankName(paymentInfo.get("bankName"))
                    .userName(paymentInfo.get("userName"))
                    .build();
        } else if (paymentMode.equalsIgnoreCase("CardPayment")) {
            paymentManager = CardBasedPayment
                    .builder()
                    .bankName(paymentInfo.get("bankName"))
                    .cardNumber(paymentInfo.get("cardNumber"))
                    .build();
        } else throw new RuntimeException("Invalid payment mode");

        return paymentManager;
    }
}
