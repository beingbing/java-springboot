package be.springboot.pp.designpattern.creational.abstractfactory.correctimplementation.os;

public class InterfaceConfigurer {

    public static GraphicalUserInterface getFactory(OsType osType) {
        return switch (osType) {
            case WINDOWS -> new WindowsInterface();
            case MAC -> new MacInterface();
            default -> throw new IllegalArgumentException("Unknown OS type: " + osType);
        };
    }
}
