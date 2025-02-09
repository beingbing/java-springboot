package be.springboot.pp.designpattern.structural.decorator.pizzabilling;

import be.springboot.pp.designpattern.structural.decorator.pizzabilling.decorators.Mushrooms;
import be.springboot.pp.designpattern.structural.decorator.pizzabilling.decorators.Olive;
import be.springboot.pp.designpattern.structural.decorator.pizzabilling.decorators.Onion;
import be.springboot.pp.designpattern.structural.decorator.pizzabilling.pizzabase.Pizza;
import be.springboot.pp.designpattern.structural.decorator.pizzabilling.pizzabase.ThinCrust;
import be.springboot.pp.designpattern.structural.decorator.pizzabilling.pizzabase.WheatBase;

public class Tester {

    public static void main(String[] args) {
        Pizza thinCrustPizza = new ThinCrust();
        System.out.println(thinCrustPizza.getDescription() + " -> ₹" + thinCrustPizza.getCost());

        thinCrustPizza = new Olive(thinCrustPizza);
        System.out.println(thinCrustPizza.getDescription() + " -> ₹" + thinCrustPizza.getCost());

        thinCrustPizza = new Onion(thinCrustPizza);
        System.out.println(thinCrustPizza.getDescription() + " -> ₹" + thinCrustPizza.getCost());

        thinCrustPizza = new Mushrooms(thinCrustPizza);
        System.out.println(thinCrustPizza.getDescription() + " -> ₹" + thinCrustPizza.getCost());

        System.out.println();

        Pizza wheatBasePizza = new WheatBase();
        System.out.println(wheatBasePizza.getDescription() + " -> ₹" + wheatBasePizza.getCost());

        wheatBasePizza = new Olive(wheatBasePizza);
        System.out.println(wheatBasePizza.getDescription() + " -> ₹" + wheatBasePizza.getCost());

        wheatBasePizza = new Onion(wheatBasePizza);
        System.out.println(wheatBasePizza.getDescription() + " -> ₹" + wheatBasePizza.getCost());

        wheatBasePizza = new Mushrooms(wheatBasePizza);
        System.out.println(wheatBasePizza.getDescription() + " -> ₹" + wheatBasePizza.getCost());
    }
}
