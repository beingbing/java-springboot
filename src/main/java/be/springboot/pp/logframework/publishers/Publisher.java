package be.springboot.pp.logframework.publishers;

import be.springboot.pp.logframework.enums.LogLevel;
import be.springboot.pp.logframework.subscribers.Subscriber;

public interface Publisher {
    boolean subscribe(LogLevel type, Subscriber subscriber);
    boolean unsubscribe(LogLevel type, Subscriber subscriber);
    void publish(LogLevel type, String news); // only notifies interested subscribers.
}
