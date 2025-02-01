package be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.coffee;

import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.Ingredients;
import lombok.ToString;

@ToString
public class Cappuccino extends Coffee {
    private final Ingredients ingredients;
    public Cappuccino(Ingredients ingredients) {
        super(ingredients);
        this.ingredients = ingredients;
    }

    @Override
    public void brew() {
        System.out.println("Cappuccino is brewed");
    }

    @Override
    public void boil() {
        System.out.println("Cappuccino is boiled");
    }
}
