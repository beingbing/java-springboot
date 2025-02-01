package be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.os;

import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.button.Button;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.button.WindowsButton;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.checkbox.Checkbox;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.checkbox.WindowsCheckbox;

public class WindowsInterface implements GraphicalUserInterface {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
