package be.springboot.pp.logframework;

import be.springboot.pp.logframework.chain.AnalyticsLogger;
import be.springboot.pp.logframework.chain.DebugLogger;
import be.springboot.pp.logframework.chain.ErrorLogger;
import be.springboot.pp.logframework.chain.FatalLogger;
import be.springboot.pp.logframework.chain.IdleLogger;
import be.springboot.pp.logframework.chain.InfoLogger;
import be.springboot.pp.logframework.chain.Logger;
import be.springboot.pp.logframework.chain.WarnLogger;
import be.springboot.pp.logframework.enums.LogLevel;
import be.springboot.pp.logframework.publishers.LogPublisher;
import be.springboot.pp.logframework.publishers.Publisher;
import be.springboot.pp.logframework.subscribers.ConsoleSubscriber;
import be.springboot.pp.logframework.subscribers.FileSubscriber;
import be.springboot.pp.logframework.subscribers.Subscriber;

import java.util.EnumSet;

public class Tester {

    public static void main(String[] args) {
        Publisher debugInfoPublisher = new LogPublisher();
        Subscriber consoleSubscriber = new ConsoleSubscriber("console");
        EnumSet.of(LogLevel.DEBUG, LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR, LogLevel.FATAL).forEach(level -> debugInfoPublisher.subscribe(level, consoleSubscriber));

        Publisher warnAndAbovePublisher = new LogPublisher();
        String filename = "src/main/java/be/springboot/pp/logframework/secure_data.txt";
        Subscriber fileSubscriber = new FileSubscriber(filename);
        EnumSet.of(LogLevel.WARN, LogLevel.ERROR, LogLevel.FATAL).forEach(level -> warnAndAbovePublisher.subscribe(level, fileSubscriber));

        Logger idleLogger = new IdleLogger();
        Logger fatalLogger = new FatalLogger(idleLogger, warnAndAbovePublisher);
        Logger analyticsLogger = new AnalyticsLogger(fatalLogger, warnAndAbovePublisher);
        Logger errorLogger = new ErrorLogger(analyticsLogger, warnAndAbovePublisher);
        Logger warnLogger = new WarnLogger(errorLogger, warnAndAbovePublisher);
        Logger infoLogger = new InfoLogger(warnLogger, debugInfoPublisher);
        Logger logger = new DebugLogger(infoLogger, debugInfoPublisher);

        /*
        use of chain-of-responsibility doesn't make sense over observer
        pattern. only observer would have done the job.
        */
        logger.log(LogLevel.ERROR, "DB query failed");
        logger.log(LogLevel.DEBUG, "received an empty response");
    }
}
