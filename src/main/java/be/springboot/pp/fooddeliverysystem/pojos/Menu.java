package be.springboot.pp.fooddeliverysystem.pojos;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import lombok.Getter;

import java.util.List;

@Getter
public class Menu {
    private final List<FoodItem> foodItemList;

    public Menu(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }
}
