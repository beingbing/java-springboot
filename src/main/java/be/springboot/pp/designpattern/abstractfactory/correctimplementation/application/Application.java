package be.springboot.pp.designpattern.abstractfactory.correctimplementation.application;

import be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.button.Button;
import be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.checkbox.Checkbox;
import be.springboot.pp.designpattern.abstractfactory.correctimplementation.os.GUIFactory;
import lombok.ToString;

@ToString
public class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(GUIFactory factory) {
        // Use the factory to create products
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void render() {
        button.render();
        checkbox.render();
    }
}
