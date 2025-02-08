package be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.subscribers;

import be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.publishers.Publisher;

public interface Subscriber {
//    void update(int runs, int wickets, float overs);
    // keeping these parameters restricts us to compulsorily send
    // all the information. What if a subscriber doesn't need
    // to know about a change in all of them ?

    // we can just send the subscriber an update ping, then using
    // getters subscriber can get the needed information from
    // publisher
    void update(Publisher publisher);
    // this will make notification of type push, but data will
    // be of type pull.
}
