package be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application;

import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.button.Button;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.checkbox.Checkbox;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.os.GraphicalUserInterface;
import lombok.ToString;

@ToString
public class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(GraphicalUserInterface gui) {
        // Use the factory to create products
        this.button = gui.createButton();
        this.checkbox = gui.createCheckbox();
    }

    public void render() {
        button.render();
        checkbox.render();
    }
}
