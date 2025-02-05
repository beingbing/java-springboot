package be.springboot.pp.fooddeliverysystem.pojos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private final Long id;
    private final String name;
    private final Address address;
    private final String phoneNumber;
    private final String email;
}
