package be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.coffee;

import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.Ingredients;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Coffee {
    private final Ingredients ingredients;

    public Coffee(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public abstract void brew();

    public abstract void boil();
}
