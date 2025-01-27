package be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.milk;

import lombok.ToString;

@ToString
public class BuffaloMilk implements Milk {
    public BuffaloMilk() {
        System.out.println("Buffalo milk added");
    }

    @Override
    public void addMilk() {
        System.out.println("adding buffalo milk");
    }
}
