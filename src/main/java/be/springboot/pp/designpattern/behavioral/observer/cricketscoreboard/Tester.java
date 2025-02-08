package be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard;

import be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.publishers.EspnCricketScoreBoardPublisher;
import be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.publishers.SonyCricketScoreBoardPublisher;
import be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.subscribers.ProjectedScoreSubscriber;
import be.springboot.pp.designpattern.behavioral.observer.cricketscoreboard.correctimpl.subscribers.RunRateBoardSubscriber;

import java.util.List;

public class Tester {

    public static void main(String[] args) {
        EspnCricketScoreBoardPublisher espnPublisher = new EspnCricketScoreBoardPublisher();
        SonyCricketScoreBoardPublisher sonyPublisher = new SonyCricketScoreBoardPublisher();
        ProjectedScoreSubscriber projectScore = new ProjectedScoreSubscriber(List.of(espnPublisher, sonyPublisher));
        RunRateBoardSubscriber runRateBoard = new RunRateBoardSubscriber(List.of(espnPublisher, sonyPublisher));
        espnPublisher.subscribe(projectScore);
        espnPublisher.subscribe(runRateBoard);
        sonyPublisher.subscribe(projectScore);
        sonyPublisher.subscribe(runRateBoard);

        espnPublisher.notifyAll(10, 0, 1.2f);
        sonyPublisher.notifyAll(17, 1, 2.4f);
        projectScore.getPublishers().forEach(publisher -> publisher.unsubscribe(projectScore));
        sonyPublisher.notifyAll(23, 1, 3.0f);
    }
}
