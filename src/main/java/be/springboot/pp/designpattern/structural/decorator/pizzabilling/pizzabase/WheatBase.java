package be.springboot.pp.designpattern.structural.decorator.pizzabilling.pizzabase;

public class WheatBase implements Pizza {
    @Override
    public String getDescription() {
        return "Wheat Base";
    }

    @Override
    public double getCost() {
        return 60;
    }
}
