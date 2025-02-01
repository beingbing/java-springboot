package be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.bean;

import lombok.ToString;

@ToString
public class FrenchBean implements Bean {
    public FrenchBean() {
        System.out.println("French bean object created");
    }

    @Override
    public void addBean() {
        System.out.println("adding french beans");
    }
}
