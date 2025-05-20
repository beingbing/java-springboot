package be.springboot.pp.ecommerce.filter;

import be.springboot.pp.ecommerce.Product;

import java.util.List;

public interface ProductFilter {
    List<Product> filter(List<Product> products);
}
