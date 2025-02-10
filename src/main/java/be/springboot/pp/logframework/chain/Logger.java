package be.springboot.pp.logframework.chain;

import be.springboot.pp.logframework.enums.LogLevel;

public interface Logger {
    void log(LogLevel logLevel, String message);
}
