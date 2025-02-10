package be.springboot.pp.logframework.chain;

import be.springboot.pp.logframework.enums.LogLevel;
import be.springboot.pp.logframework.publishers.Publisher;

public class DebugLogger implements Logger {
    private final Logger nextLogger;
    private final Publisher publisher;

    public DebugLogger(Logger nextLogger, Publisher publisher) {
        this.nextLogger = nextLogger;
        this.publisher = publisher;
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if (logLevel.getLevel() == LogLevel.DEBUG.getLevel()) {
            this.publisher.publish(logLevel, message);
        }

        this.nextLogger.log(logLevel, message);
    }
}
