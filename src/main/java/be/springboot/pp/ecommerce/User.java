package be.springboot.pp.ecommerce;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private final int id;
    private final String name;
    private final String email;
    private final String phoneNumber;
}
