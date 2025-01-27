package be.springboot.pp.designpattern.abstractfactory.correctimplementation;

import be.springboot.pp.designpattern.abstractfactory.correctimplementation.application.Application;
import be.springboot.pp.designpattern.abstractfactory.correctimplementation.os.FactoryConfigurer;
import be.springboot.pp.designpattern.abstractfactory.correctimplementation.os.OsType;

public class Tester {

    public static void main(String[] args) {
        Application app = new Application(FactoryConfigurer.getFactory(OsType.WINDOWS));
        System.out.println("app: " + app);
        app.render();
    }
}
