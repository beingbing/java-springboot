package be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.sugar;

import lombok.ToString;

@ToString
public class BrownSugar implements Sugar {
    public BrownSugar() {
        System.out.println("Brown Sugar added");
    }

    @Override
    public void addSugar() {
        System.out.println("adding brown sugar");
    }
}
