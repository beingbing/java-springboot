package be.springboot.pp.ecommerce.command.impl;

import be.springboot.pp.ecommerce.command.Command;
import be.springboot.pp.ecommerce.dto.ProductCopy;
import be.springboot.pp.ecommerce.dto.User;

import java.util.List;

public class BulkAddProductsCommand implements Command {
    private final User user;
    private final List<ProductCopy> productCopies;

    public BulkAddProductsCommand(User user, List<ProductCopy> productCopies) {
        this.user = user;
        this.productCopies = productCopies;
    }

    @Override
    public void execute() {
        //
    }
}
