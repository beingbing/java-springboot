package be.springboot.pp.ecommerce.filter.impl;

import be.springboot.pp.ecommerce.Product;
import be.springboot.pp.ecommerce.filter.ProductFilter;

import java.util.ArrayList;
import java.util.List;

public class PayOnDeliveryFilter implements ProductFilter {
    private final boolean isPayOnDelivery;
    private final ProductFilter nextFilter;

    public PayOnDeliveryFilter(boolean isPayOnDelivery, ProductFilter nextFilter) {
        this.isPayOnDelivery = isPayOnDelivery;
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        List<Product> filteredProducts = nextFilter.filter(products);
        List<Product> payOnDeliveryProducts = new ArrayList<>();
        for (Product product : filteredProducts) {
            if (product.isPayOnDelivery() == isPayOnDelivery) {
                payOnDeliveryProducts.add(product);
            }
        }
        return payOnDeliveryProducts;
    }
}
