package be.springboot.pp.designpattern.structural.decorator.coffeebilling.decorators;

import be.springboot.pp.designpattern.structural.decorator.coffeebilling.coffeebase.Coffee;

public abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}
