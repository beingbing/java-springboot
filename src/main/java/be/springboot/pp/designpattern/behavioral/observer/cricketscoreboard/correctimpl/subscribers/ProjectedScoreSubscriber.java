package be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.subscribers;

import be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.publishers.Publisher;

import java.util.List;

public class ProjectedScoreSubscriber implements Subscriber {
    private int runs;
    private int wickets;
    private float overs;
    private final List<Publisher> publishers;

    public ProjectedScoreSubscriber(List<Publisher> publishers) {
        this.publishers = publishers;
        for (Publisher publisher : this.publishers)
            publisher.subscribe(this);
    }

    @Override
    public void update(Publisher publisher) {
        this.runs = publisher.getRuns();
        this.wickets = publisher.getWickets();
        this.overs = publisher.getOvers();
        // additional logic to do other stuff
        System.out.println("ProjectedScoreSubscriber: update: " + runs + " " + wickets + " " + overs);
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public float getOvers() {
        return overs;
    }

    public int getWickets() {
        return wickets;
    }

    public int getRuns() {
        return runs;
    }
}
