package be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.publishers;

import be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.subscribers.Subscriber;

public interface Publisher {
    void notifyAll(int runs, int wickets, float overs);
    boolean subscribe(Subscriber subscriber);
    boolean unsubscribe(Subscriber subscriber);
    int getRuns();
    int getWickets();
    float getOvers();
}
