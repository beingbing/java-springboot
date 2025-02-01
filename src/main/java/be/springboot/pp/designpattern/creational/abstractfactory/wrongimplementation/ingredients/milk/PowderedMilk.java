package be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.milk;

import lombok.ToString;

@ToString
public class PowderedMilk implements Milk {
    public PowderedMilk() {
        System.out.println("Powdered milk added");
    }

    @Override
    public void addMilk() {
        System.out.println("adding powdered milk");
    }
}
