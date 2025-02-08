package be.springboot.pp.designpattern.behavioral.observer.newsletter;

import be.springboot.pp.designpattern.behavioral.observer.newsletter.enums.NewsType;
import be.springboot.pp.designpattern.behavioral.observer.newsletter.publishers.NewsAgency;
import be.springboot.pp.designpattern.behavioral.observer.newsletter.subscribers.User;

import java.util.EnumSet;

public class Tester {

    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        User alice = new User("Alice");
        EnumSet.of(NewsType.SPORTS, NewsType.TECHNOLOGY).forEach(type -> agency.subscribe(type, alice));

        User bob = new User("Bob");
        EnumSet.of(NewsType.POLITICS).forEach(type -> agency.subscribe(type, bob));

        User charlie = new User("Charlie");
        EnumSet.allOf(NewsType.class).forEach(type -> agency.subscribe(type, charlie));

        // Publish News
        agency.publishNews(NewsType.SPORTS, "Ronaldo scores a hat-trick!");
        agency.publishNews(NewsType.POLITICS, "New election results announced!");
        agency.publishNews(NewsType.TECHNOLOGY, "Apple unveils new iPhone!");

        agency.unsubscribe(NewsType.SPORTS, alice);

        agency.publishNews(NewsType.SPORTS, "Cristiano Ronaldo scores in finals!");
    }
}
