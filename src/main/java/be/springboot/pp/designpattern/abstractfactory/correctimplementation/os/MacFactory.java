package be.springboot.pp.designpattern.abstractfactory.correctimplementation.os;

import be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.button.Button;
import be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.button.MacButton;
import be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.checkbox.Checkbox;
import be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.checkbox.MacCheckbox;

public class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
