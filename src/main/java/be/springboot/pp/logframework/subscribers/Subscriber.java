package be.springboot.pp.logframework.subscribers;

import be.springboot.pp.logframework.enums.LogLevel;

public interface Subscriber {
    void update(LogLevel type, String news);
}
