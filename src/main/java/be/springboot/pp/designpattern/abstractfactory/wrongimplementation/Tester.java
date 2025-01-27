package be.springboot.pp.designpattern.abstractfactory.wrongimplementation;

import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.coffee.Coffee;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.coffee.CoffeeFactory;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.coffee.CoffeeType;

public class Tester {

    public static void main(String[] args) {
        CoffeeFactory coffeeFactory = new CoffeeFactory();

        Coffee cappuccino = coffeeFactory.getCoffee(CoffeeType.CAPPUCCINO);
        System.out.println("coffee is: " + cappuccino);

        Coffee espresso = coffeeFactory.getCoffee(CoffeeType.ESPRESSO);
        System.out.println("coffee is: " + espresso);
    }
}
