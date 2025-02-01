package be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.coffee;

import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.Ingredients;
import lombok.ToString;

@ToString
public class Robusta extends Coffee {
    Ingredients ingredients;
    public Robusta(Ingredients ingredients) {
        super(ingredients);
        this.ingredients = ingredients;
    }

    @Override
    public void brew() {
        System.out.println("Robusta is brewed");
    }

    @Override
    public void boil() {
        System.out.println("Robusta is boiled");
    }
}
