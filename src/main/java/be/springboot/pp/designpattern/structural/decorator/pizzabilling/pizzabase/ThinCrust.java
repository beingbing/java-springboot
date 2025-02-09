package be.springboot.pp.designpattern.structural.decorator.pizzabilling.pizzabase;

public class ThinCrust implements Pizza {
    @Override
    public String getDescription() {
        return "Thin Crust";
    }

    @Override
    public double getCost() {
        return 50.0; // Base price
    }
}
