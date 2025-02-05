package be.springboot.pp.fooddeliverysystem.dtos;

import be.springboot.pp.fooddeliverysystem.enums.CuisineType;
import be.springboot.pp.fooddeliverysystem.enums.MealType;
import be.springboot.pp.fooddeliverysystem.enums.StarRating;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FoodItem {
    private final int id;
    private final String name;
    private final String description;
    private final Double priceInInr;
    private final MealType mealType;
    private final CuisineType cuisineType;
    private final StarRating starRating;
    private final Long restaurantId;
    private final boolean isAvailable;
}
