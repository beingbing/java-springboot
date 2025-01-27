package be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.checkbox;

import lombok.ToString;

@ToString
public class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Windows Checkbox");
    }
}
