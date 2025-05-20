package be.springboot.pp.ecommerce;

import java.util.List;
import java.util.Optional;

public class SearchProductAPI {
    private final ProductSearcher productSearcher;

    public SearchProductAPI(ProductSearcher productSearcher) {
        this.productSearcher = productSearcher;
    }

    public List<Product> search(String productName, FilterDetails filterDetails, User user) {
        Optional<Permission> permission = PermissionFactory.getSearchPermission(user);
        if (!permission.isPresent() || !permission.get().isPermitted()) throw new RuntimeException("permission denied!!");
        return productSearcher.searchProduct(productName, filterDetails);
    }
}
