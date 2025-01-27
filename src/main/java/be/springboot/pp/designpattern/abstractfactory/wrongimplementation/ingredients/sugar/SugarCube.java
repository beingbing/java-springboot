package be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.sugar;

import lombok.ToString;

@ToString
public class SugarCube implements Sugar {
    public SugarCube() {
        System.out.println("Sugar Cube added");
    }

    @Override
    public void addSugar() {
        System.out.println("adding sugar cube");
    }
}
