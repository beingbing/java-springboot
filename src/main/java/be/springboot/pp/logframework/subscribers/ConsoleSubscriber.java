package be.springboot.pp.logframework.subscribers;

import be.springboot.pp.logframework.enums.LogLevel;

public class ConsoleSubscriber implements Subscriber {
    private final String name;

    public ConsoleSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(LogLevel newsType, String data) {
        System.out.println(name + " received " + newsType + " data: " + data);
    }
}
