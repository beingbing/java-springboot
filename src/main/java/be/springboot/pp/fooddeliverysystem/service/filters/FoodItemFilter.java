package be.springboot.pp.fooddeliverysystem.service.filters;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;

public interface FoodItemFilter {
    boolean filter(FoodItem foodItem);
}
