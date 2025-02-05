package be.springboot.pp.fooddeliverysystem.pojos;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import lombok.Getter;

@Getter
public class CartItem {
    private final FoodItem foodItem;
    private final Integer quantity;

    public CartItem(FoodItem foodItem, Integer quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }
}
