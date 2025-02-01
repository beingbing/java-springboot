package be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.os;

import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.button.Button;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.checkbox.Checkbox;

public interface GraphicalUserInterface {
    Button createButton();
    Checkbox createCheckbox();
}
