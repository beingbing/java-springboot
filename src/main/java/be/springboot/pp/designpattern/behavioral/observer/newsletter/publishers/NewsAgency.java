package be.springboot.pp.designpattern.behavioral.observer.newsletter.publishers;

import be.springboot.pp.designpattern.behavioral.observer.newsletter.enums.NewsType;
import be.springboot.pp.designpattern.behavioral.observer.newsletter.subscribers.NewsSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsAgency implements NewsPublisher {
    private final Map<NewsType, List<NewsSubscriber>> subscriberMap = new HashMap<>();

    @Override
    public boolean subscribe(NewsType type, NewsSubscriber subscriber) {
        System.out.println(subscriber + " subscribed to " + type);
        return subscriberMap.computeIfAbsent(type, k -> new ArrayList<>()).add(subscriber);
    }

    @Override
    public boolean unsubscribe(NewsType type, NewsSubscriber subscriber) {
        System.out.println(subscriber + " unsubscribed from " + type);
        if (!subscriberMap.containsKey(type)) return false;
        return subscriberMap.get(type).remove(subscriber);
    }

    @Override
    public void publishNews(NewsType newsType, String news) {
        System.out.println("\n📰 Publishing " + newsType + " News: " + news);
        if (subscriberMap.containsKey(newsType))
            for (NewsSubscriber subscriber : subscriberMap.get(newsType))
                subscriber.update(newsType, news);
    }
}
