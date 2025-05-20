package be.springboot.pp.ecommerce;

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
        return List.of();
    }
}
