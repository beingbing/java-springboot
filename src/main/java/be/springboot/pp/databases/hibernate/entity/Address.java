package be.springboot.pp.databases.hibernate.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/*
* without @Embeddable we will get below error when we try to create Application object which contains Address object
* Could not determine recommended JdbcType for Java type 'be.springboot.pp.databases.hibernate.entity.Address'
* */

@Getter
@Setter
@Embeddable // this class is not an entity, but is a part of an entity
public class Address {
    private String address;

    private String city;

    private String state;

    private String zipCode;

    private Double latitude;

    private Double longitude;
}
