package be.springboot.pp.logframework.publishers;

import be.springboot.pp.logframework.enums.LogLevel;
import be.springboot.pp.logframework.subscribers.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogPublisher implements Publisher {
    private final Map<LogLevel, List<Subscriber>> subscriberMap = new HashMap<>();

    @Override
    public boolean subscribe(LogLevel type, Subscriber subscriber) {
        System.out.println(subscriber + " subscribed to " + type);
        return subscriberMap.computeIfAbsent(type, k -> new ArrayList<>()).add(subscriber);
    }

    @Override
    public boolean unsubscribe(LogLevel type, Subscriber subscriber) {
        System.out.println(subscriber + " unsubscribed from " + type);
        if (!subscriberMap.containsKey(type)) return false;
        return subscriberMap.get(type).remove(subscriber);
    }

    @Override
    public void publish(LogLevel newsType, String news) {
        System.out.println("\n📰 Publishing " + newsType + " News: " + news);
        if (subscriberMap.containsKey(newsType))
            for (Subscriber subscriber : subscriberMap.get(newsType))
                subscriber.update(newsType, news);
    }
}
