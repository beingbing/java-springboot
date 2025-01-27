package be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.checkbox;

import lombok.ToString;

@ToString
public class MacCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering MacOS Checkbox");
    }
}
