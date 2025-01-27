package be.springboot.pp.designpattern.abstractfactory.correctimplementation.os;

import be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.button.Button;
import be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.checkbox.Checkbox;

public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
