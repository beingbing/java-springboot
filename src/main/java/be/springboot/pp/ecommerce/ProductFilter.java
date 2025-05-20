package be.springboot.pp.ecommerce;

import java.util.List;

public interface ProductFilter {
    List<Product> filter(List<Product> products);
}
