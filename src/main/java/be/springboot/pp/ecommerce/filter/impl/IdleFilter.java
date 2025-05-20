package be.springboot.pp.ecommerce.filter.impl;

import be.springboot.pp.ecommerce.dto.Product;
import be.springboot.pp.ecommerce.filter.ProductFilter;

import java.util.List;

public class IdleFilter implements ProductFilter {

    @Override
    public List<Product> filter(List<Product> products) {
        return products;
    }
}
