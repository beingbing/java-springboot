package be.springboot.pp.ecommerce.command.impl;

import be.springboot.pp.ecommerce.dto.ProductCopy;
import be.springboot.pp.ecommerce.dto.User;

public class AddProductCommand {
    private final User user;
    private final ProductCopy productCopy;

    public AddProductCommand(User user, ProductCopy productCopy) {
        this.user = user;
        this.productCopy = productCopy;
    }


}
