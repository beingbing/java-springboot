package be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients;

import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.bean.Bean;
import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.bean.GhanaBean;
import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.milk.BuffaloMilk;
import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.milk.Milk;
import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.sugar.Sugar;
import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.sugar.SugarCube;
import lombok.ToString;

@ToString
public class RobustaIngredients implements Ingredients {
    private final Bean bean;
    private final Milk milk;
    private final Sugar sugar;

    public RobustaIngredients() {
        this.bean = new GhanaBean();
        this.sugar = new SugarCube();
        this.milk = new BuffaloMilk();
    }


    @Override
    public Bean getBean() {
        return this.bean;
    }

    @Override
    public Milk getMilk() {
        return this.milk;
    }

    @Override
    public Sugar getSugar() {
        return this.sugar;
    }
}
