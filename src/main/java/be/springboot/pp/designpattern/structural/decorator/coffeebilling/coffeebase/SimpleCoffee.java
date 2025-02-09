package be.springboot.pp.designpattern.structural.decorator.coffeebilling.coffeebase;

public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Plain Coffee";
    }

    @Override
    public double getCost() {
        return 50.0; // Base price
    }
}
