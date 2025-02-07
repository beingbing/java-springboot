package be.springboot.pp.cricketscoreboard.correctimpl.subscribers;

import be.springboot.pp.cricketscoreboard.correctimpl.publishers.Publisher;

import java.util.List;

public class RunRateBoardSubscriber implements Subscriber {
    private int runs;
    private int wickets;
    private float overs;
    private List<Publisher> publishers;

    public RunRateBoardSubscriber(List<Publisher> publishers) {
        this.publishers = publishers;
        for (Publisher publisher : this.publishers)
            publisher.subscribe(this);
    }

    @Override
    public void update(Publisher publisher) {
        this.runs = publisher.getRuns();
        this.overs = publisher.getOvers();
        // additional logic to do other stuff
        System.out.println("RunRateBoardSubscriber: update: " + runs + " " + wickets + " " + overs);
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
