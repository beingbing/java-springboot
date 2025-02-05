package be.springboot.pp.fooddeliverysystem.dtos;

import be.springboot.pp.fooddeliverysystem.enums.PaymentStatus;
import lombok.Getter;

@Getter
public class PaymentResponse {
    private final double amount;
    private final Long id;
    private final PaymentStatus status;

    public PaymentResponse(double amount, Long id, PaymentStatus status) {
        this.amount = amount;
        this.id = id;
        this.status = status;
    }
}
