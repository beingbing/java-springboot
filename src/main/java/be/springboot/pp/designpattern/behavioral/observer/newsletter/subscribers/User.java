package be.springboot.pp.designpattern.behavioral.observer.newsletter.subscribers;

import be.springboot.pp.designpattern.behavioral.observer.newsletter.enums.NewsType;
import lombok.ToString;

@ToString
public class User implements NewsSubscriber {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(NewsType newsType, String news) {
        System.out.println(name + " received " + newsType + " news: " + news);
    }
}
