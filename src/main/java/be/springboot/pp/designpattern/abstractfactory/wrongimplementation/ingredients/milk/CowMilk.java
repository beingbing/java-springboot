package be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.milk;

import lombok.ToString;

@ToString
public class CowMilk implements Milk {
    public CowMilk() {
        System.out.println("Cow milk added");
    }

    @Override
    public void addMilk() {
        System.out.println("adding cow milk");
    }
}
