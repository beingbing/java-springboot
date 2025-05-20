package be.springboot.pp.ecommerce;

import lombok.Getter;

@Getter
public class ProductCopy {
    private final Product product;
    private final int id;
    private final boolean isSold;

    public ProductCopy(Product product, int id, boolean isSold) {
        this.product = product;
        this.id = id;
        this.isSold = isSold;
    }
}
