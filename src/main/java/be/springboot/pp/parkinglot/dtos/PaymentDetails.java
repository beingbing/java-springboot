package be.springboot.pp.parkinglot.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentDetails {
    private final String cardScheme;
    private final String name;
    private final String pin;
    private final Double amount;
}
