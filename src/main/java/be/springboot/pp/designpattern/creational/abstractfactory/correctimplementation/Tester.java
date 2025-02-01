package be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation;

import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.application.Application;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.os.InterfaceConfigurer;
import be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.os.OsType;

public class Tester {

    public static void main(String[] args) {
        Application app = new Application(InterfaceConfigurer.getFactory(OsType.WINDOWS));
        System.out.println("app: " + app);
        app.render();
    }
}
