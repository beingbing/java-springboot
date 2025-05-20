package be.springboot.pp.ecommerce;

import java.util.List;

public class ProductSearcher {
    public List<Product> searchProduct(String productName, FilterDetails filterDetails) {
        List<Product> products = DbAccessor.getProductsByName(productName);

    }
}
