package be.springboot.pp.fooddeliverysystem.dtos;

import be.springboot.pp.fooddeliverysystem.enums.CuisineType;
import be.springboot.pp.fooddeliverysystem.enums.MealType;
import be.springboot.pp.fooddeliverysystem.enums.StarRating;
import be.springboot.pp.fooddeliverysystem.pojos.Address;
import be.springboot.pp.fooddeliverysystem.pojos.BusinessHours;
import be.springboot.pp.fooddeliverysystem.pojos.Menu;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Restaurant {
    private final Long id;
    private final String name;
    private final String description;
    private final BusinessHours businessHours;
    private final MealType mealType;
    private final List<CuisineType> cuisineTypes;
    private final StarRating starRating;
    private final Menu menu;
    private final Address address;
}
