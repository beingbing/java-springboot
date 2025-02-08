package be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.publishers;

import be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.subscribers.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class EspnCricketScoreBoardPublisher implements Publisher {
    private int runs;
    private int wickets;
    private float overs;
    private final List<Subscriber> subscribers;

    public EspnCricketScoreBoardPublisher() {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void notifyAll(int runs, int wickets, float overs) {
        System.out.println("EspnCricketScoreBoardPublisher: notifyAll: runs: " + runs + " wickets: " + wickets + " overs: " + overs);
        this.runs = runs;
        this.wickets = wickets;
        this.overs = overs;
        for (Subscriber subscriber : subscribers) {
            subscriber.update(this);
        }
    }

    @Override
    public boolean subscribe(Subscriber subscriber) {
        return this.subscribers.add(subscriber);
    }

    @Override
    public boolean unsubscribe(Subscriber subscriber) {
        return this.subscribers.remove(subscriber);
    }

    @Override
    public float getOvers() {
        return overs;
    }

    @Override
    public int getWickets() {
        return wickets;
    }

    @Override
    public int getRuns() {
        return runs;
    }
}
