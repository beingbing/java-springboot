package be.springboot.pp.ecommerce;

public class AddToCartPermission implements Permission{
    @Override
    public boolean isPermitted() {
        return false;
    }
}
