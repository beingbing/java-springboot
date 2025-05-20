package be.springboot.pp.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Address {
    private final String addressLine1;
    private final String addressLine2;
    private final String addressLine3;
    private final String city;
    private final String state;
    private final String zip;
    private final String country;
}
