package be.springboot.pp.designpattern.structural.decorator.pizzabilling.decorators;

import be.springboot.pp.designpattern.structural.decorator.pizzabilling.pizzabase.Pizza;

public abstract class PizzaToppings implements Pizza {
    private final Pizza pizzaWithoutToppings;

    protected PizzaToppings(Pizza pizza) {
        this.pizzaWithoutToppings = pizza;
    }

    @Override
    public String getDescription() {
        return pizzaWithoutToppings.getDescription();
    }

    @Override
    public double getCost() {
        return pizzaWithoutToppings.getCost();
    }
}
