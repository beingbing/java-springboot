package be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients;

import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.bean.Bean;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.bean.FrenchBean;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.milk.Milk;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.milk.PowderedMilk;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.sugar.Sugar;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.sugar.WhiteSugar;
import lombok.ToString;

@ToString
public class EspressoIngredients implements Ingredients {
    private final Bean bean;
    private final Milk milk;
    private final Sugar sugar;

    public EspressoIngredients() {
        this.bean = new FrenchBean();
        this.sugar = new WhiteSugar();
        this.milk = new PowderedMilk();
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
