package be.springboot.pp.designpattern.structural.decorator.coffeebilling.decorators;

import be.springboot.pp.designpattern.structural.decorator.coffeebilling.coffeebase.Coffee;

public class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Whipped Cream";
    }

    @Override
    public double getCost() {
        return super.getCost() + 15.0;
    }
}
