package be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients;

import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.bean.Bean;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.milk.Milk;
import be.springboot.pp.designpattern.abstractfactory.wrongimplementation.ingredients.sugar.Sugar;

public interface Ingredients {
    Bean getBean();
    Milk getMilk();
    Sugar getSugar();
}
