package be.springboot.pp.fooddeliverysystem.pojos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Address {
    private final String addressLine1;
    private final String addressLine2;
    private final String addressLine3;
    private final String city;
    private final String state;
    private final String zip;
    private final String country;
    private final Long latitude;
    private final Long longitude;
}
