package be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.button;

import lombok.ToString;

@ToString
public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Windows Button");
    }
}
