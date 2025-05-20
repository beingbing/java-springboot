package be.springboot.pp.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cart {
    private final int id;
    private final List<ProductCopy> productCopies;

    public Cart(int id) {
        this.id = id;
        productCopies = new ArrayList<>();
    }

    public void add(ProductCopy productCopy) {
        this.productCopies.add(productCopy);
    }

    public void remove(ProductCopy productCopy) {
        if (!this.productCopies.contains(productCopy)) throw new RuntimeException("product not found");
        this.productCopies.remove(productCopy);
    }

    public Double getCartAmount() {
        Double cartAmount = 0.0;
        for (ProductCopy productCopy : productCopies) {
            cartAmount += productCopy.getProduct().getPrice();
        }
        return cartAmount;
    }

    public int getDistinctItemCount() {
        Set<Integer> distinctItemIds = productCopies.stream().map(ProductCopy::getId).distinct().collect(Collectors.toSet());
        return distinctItemIds.size();
    }

    public int getTotalItemCount() {
        return productCopies.size();
    }
}
