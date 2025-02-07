package be.springboot.pp.cricketscoreboard;

import be.springboot.pp.cricketscoreboard.correctimpl.publishers.EspnCricketScoreBoardPublisher;
import be.springboot.pp.cricketscoreboard.correctimpl.subscribers.ProjectedScoreSubscriber;
import be.springboot.pp.cricketscoreboard.correctimpl.subscribers.RunRateBoardSubscriber;

import java.util.List;

public class Tester {

    public static void main(String[] args) {
        EspnCricketScoreBoardPublisher espnPublisher = new EspnCricketScoreBoardPublisher();
        EspnCricketScoreBoardPublisher sonyPublisher = new EspnCricketScoreBoardPublisher();
        ProjectedScoreSubscriber projectScore = new ProjectedScoreSubscriber(List.of(espnPublisher, sonyPublisher));
        RunRateBoardSubscriber runRateBoard = new RunRateBoardSubscriber(List.of(espnPublisher, sonyPublisher));
        publisher.subscribe(projectScore);
        publisher.subscribe(runRateBoard);

        publisher.notifyAll(10, 0, 1.2f);
        publisher.notifyAll(17, 1, 2.4f);
        projectScore.getPublishers().unsubscribe(projectScore);
        publisher.notifyAll(23, 1, 3.0f);
    }
}
