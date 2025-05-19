package be.springboot.pp.designpattern.creational.singleton;

public class Tester {

    public static void main(String[] args) {
        System.out.println(AppConfigSingleton.getInstance().get("spring.application.name"));
        AppConfigSingleton o1, o2;
        o1 = AppConfigSingleton.getInstance();
        o2 = AppConfigSingleton.getInstance();
        System.out.println(o1 == o2);
    }
}
