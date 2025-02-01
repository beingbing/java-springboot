package be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients;

import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.bean.AmericanBean;
import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.bean.Bean;
import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.milk.CowMilk;
import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.milk.Milk;
import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.sugar.BrownSugar;
import be.springboot.pp.designpattern.creational.abstractfactory.wrongimplementation.ingredients.sugar.Sugar;
import lombok.ToString;

@ToString
public class CappuccinoIngredients implements Ingredients {
    private final Bean bean;
    private final Milk milk;
    private final Sugar sugar;

    public CappuccinoIngredients() {
        this.bean = new AmericanBean();
        this.sugar = new BrownSugar();
        this.milk = new CowMilk();
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
