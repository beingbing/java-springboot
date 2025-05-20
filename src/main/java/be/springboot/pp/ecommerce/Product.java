package be.springboot.pp.ecommerce;

import lombok.Builder;

@Builder
public class Product {
    private final int id;
    private final String name;
    private final String description;
    private final double price;
    private final Rating rating;
    private final boolean payOnDelivery;
}
