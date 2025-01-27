package be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.sugar;

import lombok.ToString;

@ToString
public class WhiteSugar implements Sugar {
    public WhiteSugar() {
        System.out.println("White sugar added");
    }

    @Override
    public void addSugar() {
        System.out.println("adding white sugar");
    }
}
