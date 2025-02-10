package be.springboot.pp.logframework.chain;

import be.springboot.pp.logframework.enums.LogLevel;

public class IdleLogger implements Logger {
    @Override
    public void log(LogLevel logLevel, String message) {
        System.out.println("All handlers executed successfully");
    }
}
