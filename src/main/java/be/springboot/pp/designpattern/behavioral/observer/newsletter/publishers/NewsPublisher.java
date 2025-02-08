package be.springboot.pp.designpattern.behavioral.observer.newsletter.publishers;

import be.springboot.pp.designpattern.behavioral.observer.newsletter.enums.NewsType;
import be.springboot.pp.designpattern.behavioral.observer.newsletter.subscribers.NewsSubscriber;

public interface NewsPublisher {
    boolean subscribe(NewsType type, NewsSubscriber subscriber);
    boolean unsubscribe(NewsType type, NewsSubscriber subscriber);
    void publishNews(NewsType type, String news); // only notifies interested subscribers.
}
