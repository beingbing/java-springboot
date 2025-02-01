package be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.bean;

import lombok.ToString;

@ToString
public class GhanaBean implements Bean {
    public GhanaBean() {
        System.out.println("Ghana bean object is created");
    }

    @Override
    public void addBean() {
        System.out.println("adding ghana beans");
    }
}
