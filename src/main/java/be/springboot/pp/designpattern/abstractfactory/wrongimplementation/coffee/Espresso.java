package be.springboot.pp.designpattern.abstractfactory.wrongimplementation.coffee;

import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.Ingredients;
import lombok.ToString;

@ToString
public class Espresso extends Coffee {
    Ingredients ingredients;
    public Espresso(Ingredients ingredients) {
        super(ingredients);
        this.ingredients = ingredients;
    }

    @Override
    public void brew() {
        System.out.println("Espresso is brewed");
    }

    @Override
    public void boil() {
        System.out.println("Espresso is boiled");
    }
}
