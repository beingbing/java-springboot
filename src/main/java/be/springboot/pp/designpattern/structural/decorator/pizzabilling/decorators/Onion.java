package be.springboot.pp.designpattern.structural.decorator.pizzabilling.decorators;

import be.springboot.pp.designpattern.structural.decorator.pizzabilling.pizzabase.Pizza;

public class Onion extends PizzaToppings {
    public Onion(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Onion";
    }

    @Override
    public double getCost() {
        return super.getCost() + 10.0;
    }
}
