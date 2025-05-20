package be.springboot.pp.ecommerce.filter.impl;

import be.springboot.pp.ecommerce.Product;
import be.springboot.pp.ecommerce.filter.ProductFilter;

import java.util.ArrayList;
import java.util.List;

public class PriceFilter implements ProductFilter {
    private final Double priceUpperCap;
    private final ProductFilter nextFilter;

    public PriceFilter(Double priceUpperCap, ProductFilter nextFilter) {
        this.priceUpperCap = priceUpperCap;
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        List<Product> filteredProducts = nextFilter.filter(products);
        List<Product> priceFilteredProducts = new ArrayList<>();
        for (Product product : filteredProducts) {
            if (product.getPrice() <= priceUpperCap) {
                priceFilteredProducts.add(product);
            }
        }
        return priceFilteredProducts;
    }
}
