package be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.os;

import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.button.Button;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.button.MacButton;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.checkbox.Checkbox;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.checkbox.MacCheckbox;

public class MacInterface implements GraphicalUserInterface {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
