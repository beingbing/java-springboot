package be.springboot.pp.designpattern.structural.decorator.pizzabilling.decorators;

import be.springboot.pp.designpattern.structural.decorator.pizzabilling.pizzabase.Pizza;

public class Olive extends PizzaToppings {
    public Olive(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Olive";
    }

    @Override
    public double getCost() {
        return super.getCost() + 7.0;
    }
}
