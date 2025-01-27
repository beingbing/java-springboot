package be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.bean;

import lombok.ToString;

@ToString
public class AmericanBean implements Bean {
    public AmericanBean() {
        System.out.println("american bean object created");
    }

    @Override
    public void addBean() {
        System.out.println("adding american beans");
    }
}
