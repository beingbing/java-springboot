package be.springboot.pp.ecommerce;

import java.util.Optional;

public class AddToCartAPI {
    private final CartManager cartManager;

    public AddToCartAPI(CartManager cartManager) {
        this.cartManager = cartManager;
    }

    public void addToCart(int productId, User user) {
        ProductCopy productCopy = DbAccessor.getProductCopyById(productId);
        if (productCopy == null) throw new RuntimeException("product not found");

        Optional<Permission> permission = PermissionFactory.getAddToCartPermission(user, productCopy);
        if (!permission.isPresent() || !permission.get().isPermitted()) throw new RuntimeException("permission denied!!");

        this.cartManager.addToCart(user, productCopy);
    }
}
