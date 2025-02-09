package be.springboot.pp.designpattern.structural.decorator.pizzabilling.decorators;

import be.springboot.pp.designpattern.structural.decorator.pizzabilling.pizzabase.Pizza;

public class Mushrooms extends PizzaToppings {
    public Mushrooms(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Mushroom";
    }

    @Override
    public double getCost() {
        return super.getCost() + 12.0;
    }
}
