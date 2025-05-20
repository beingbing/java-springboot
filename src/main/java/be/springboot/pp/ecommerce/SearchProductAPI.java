package be.springboot.pp.ecommerce;

import java.util.List;

public class SearchProductAPI {
    private final ProductSearcher productSearcher;

    public SearchProductAPI(ProductSearcher productSearcher) {
        this.productSearcher = productSearcher;
    }

    public List<Product> search(String productName, FilterDetails filterDetails) {

    }
}
