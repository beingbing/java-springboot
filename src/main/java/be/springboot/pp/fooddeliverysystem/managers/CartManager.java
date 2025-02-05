package be.springboot.pp.fooddeliverysystem.managers;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.enums.PermissionType;
import be.springboot.pp.fooddeliverysystem.permissions.PermissionFactory;
import be.springboot.pp.fooddeliverysystem.permissions.Permissions;
import be.springboot.pp.fooddeliverysystem.pojos.CartItem;
import be.springboot.pp.fooddeliverysystem.pojos.DataAccessObjectConverter;
import be.springboot.pp.fooddeliverysystem.pojos.DataAccessResult;
import be.springboot.pp.fooddeliverysystem.pojos.User;
import be.springboot.pp.fooddeliverysystem.repository.DataAccessor;

import java.util.List;

public class CartManager {
    private final PermissionFactory permissionFactory = new PermissionFactory();

    public List<CartItem> getUserCart(User user) {
        DataAccessResult result = DataAccessor.getCart(user);
        return DataAccessObjectConverter.convertToCartItems(result);
    }

    public boolean addItem(User user, FoodItem foodItem) { // adds 1 unit only
        Permissions permissions = permissionFactory.getPermission(PermissionType.ADD_TO_CART, user, foodItem, null, null);
        if (!permissions.isPermitted()) throw new RuntimeException("permission denied!!");
        if (!isFoodItemFromSameRestaurant(user, foodItem)) throw new RuntimeException("You can only add items from same restaurant");
        return DataAccessor.addItemToCart(user, foodItem);
    }

    public boolean deleteItem(User user, FoodItem foodItem) {
        Permissions permissions = permissionFactory.getPermission(PermissionType.DELETE_FROM_CART, user, foodItem, null, null);
        if (!permissions.isPermitted()) throw new RuntimeException("permission denied!!");
        if (!isFoodItemPresentInCart(user, foodItem)) throw new RuntimeException("can't delete item as it doesn't exist");
        return DataAccessor.deleteItemFromCart(user, foodItem);
    }

    public boolean checkout(User user) {
        Permissions permissions = permissionFactory.getPermission(PermissionType.CHECKOUT, user, null, null, null);
        if (!permissions.isPermitted()) throw new RuntimeException("permission denied!!");
        if (isCartEmpty(user)) throw new RuntimeException("Cart is empty");
        return DataAccessor.checkoutCart(user);
    }

    private boolean isFoodItemFromSameRestaurant(User user, FoodItem foodItem) {
        List<CartItem> cartItems = getUserCart(user);
        return cartItems.isEmpty()
                || (cartItems.getFirst().getFoodItem().getRestaurantId().equals(foodItem.getRestaurantId()));
    }

    private boolean isFoodItemPresentInCart(User user, FoodItem foodItem) {
        List<CartItem> cartItems = getUserCart(user);
        for (CartItem item : cartItems) {
            if (item.getFoodItem().getId() == foodItem.getId()) return true;
        }
        return false;
    }

    private boolean isCartEmpty(User user) {
        List<CartItem> cartItems = getUserCart(user);
        return cartItems.isEmpty();
    }
}
