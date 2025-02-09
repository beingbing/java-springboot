package be.springboot.pp.designpattern.structural.decorator.coffeebilling;

import be.springboot.pp.designpattern.structural.decorator.coffeebilling.coffeebase.Coffee;
import be.springboot.pp.designpattern.structural.decorator.coffeebilling.coffeebase.SimpleCoffee;
import be.springboot.pp.designpattern.structural.decorator.coffeebilling.decorators.MilkDecorator;
import be.springboot.pp.designpattern.structural.decorator.coffeebilling.decorators.SugarDecorator;
import be.springboot.pp.designpattern.structural.decorator.coffeebilling.decorators.WhippedCreamDecorator;

public class Tester {

    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " -> ₹" + coffee.getCost());

        // Add Milk
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " -> ₹" + coffee.getCost());

        // Add Sugar
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " -> ₹" + coffee.getCost());

        // Add Whipped Cream
        coffee = new WhippedCreamDecorator(coffee);
        System.out.println(coffee.getDescription() + " -> ₹" + coffee.getCost());
    }
}
