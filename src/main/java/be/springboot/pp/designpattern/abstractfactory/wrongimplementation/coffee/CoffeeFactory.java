package be.springboot.pp.designpattern.abstractfactory.wrongimplementation.coffee;

import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.CappuccinoIngredients;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.EspressoIngredients;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.RobustaIngredients;
import lombok.ToString;

// creation responsibility of type of coffee
@ToString
public class CoffeeFactory {

    public Coffee getCoffee(CoffeeType coffeeType) {
        return switch (coffeeType) {
            case CAPPUCCINO -> new Cappuccino(new CappuccinoIngredients());
            case ESPRESSO -> new Espresso(new EspressoIngredients());
            case ROBUSTA -> new Robusta(new RobustaIngredients());
        };
    }
}
