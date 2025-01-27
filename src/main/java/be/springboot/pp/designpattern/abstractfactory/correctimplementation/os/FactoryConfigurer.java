package be.springboot.pp.designpattern.abstractfactory.correctimplementation.os;

public class FactoryConfigurer {

    public static GUIFactory getFactory(OsType osType) {
        return switch (osType) {
            case WINDOWS -> new WindowsFactory();
            case MAC -> new MacFactory();
            default -> throw new IllegalArgumentException("Unknown OS type: " + osType);
        };
    }
}
