package be.springboot.pp.fooddeliverysystem.service.filters;

import be.springboot.pp.fooddeliverysystem.dtos.Restaurant;

public interface RestaurantFilter {
    boolean filter(Restaurant restaurant);
}
