package be.springboot.pp.ecommerce.filter;

import be.springboot.pp.ecommerce.db.DbAccessor;
import be.springboot.pp.ecommerce.dto.FilterDetails;
import be.springboot.pp.ecommerce.dto.Product;

import java.util.List;

public class ProductSearcher {

    public List<Product> searchProduct(String productName, FilterDetails filterDetails) {
        List<Product> products = DbAccessor.getProductsByName(productName);
        return FilterFactory.createFilter(filterDetails).filter(products);
    }
}
