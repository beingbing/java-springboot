package be.springboot.pp.designpattern.behavioral.observer.newsletter.subscribers;

import be.springboot.pp.designpattern.behavioral.observer.newsletter.enums.NewsType;

public interface NewsSubscriber {
    void update(NewsType type, String news); // triggered when relevant news is published
}
