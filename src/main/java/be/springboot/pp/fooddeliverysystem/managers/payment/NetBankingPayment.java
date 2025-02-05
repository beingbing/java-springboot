package be.springboot.pp.fooddeliverysystem.managers.payment;

import be.springboot.pp.fooddeliverysystem.dtos.PaymentResponse;
import lombok.Builder;

@Builder
public class NetBankingPayment implements PaymentManager {
    private final String bankName;
    private final String userName;
    private final String password;
    private final String pin;
    private final Double amount;

    @Override
    public PaymentResponse executePayment() {
        return null;
    }
}
