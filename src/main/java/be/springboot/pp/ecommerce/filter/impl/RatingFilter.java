package be.springboot.pp.ecommerce.filter.impl;

import be.springboot.pp.ecommerce.Product;
import be.springboot.pp.ecommerce.Rating;
import be.springboot.pp.ecommerce.filter.ProductFilter;

import java.util.ArrayList;
import java.util.List;

public class RatingFilter implements ProductFilter {
    private final Rating minRating;
    private final ProductFilter nextFilter;

    public RatingFilter(Rating minRating, ProductFilter nextFilter) {
        this.minRating = minRating;
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        List<Product> filteredProducts = nextFilter.filter(products);
        List<Product> ratingFilteredProducts = new ArrayList<>();
        for (Product product : filteredProducts) {
            if (product.getRating().getValue() >= minRating.getValue()) {
                ratingFilteredProducts.add(product);
            }
        }
        return ratingFilteredProducts;
    }
}
