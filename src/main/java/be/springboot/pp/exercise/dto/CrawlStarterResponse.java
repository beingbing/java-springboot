package be.springboot.pp.exercise.dto;

import be.springboot.pp.exercise.enums.CrawlProcessStatus;

public class CrawlStarterResponse {

    private int crawlProcessId;

    private CrawlProcessStatus crawlProcessStatus;

    public CrawlStarterResponse(int crawlProcessId, CrawlProcessStatus crawlProcessStatus) {
        this.crawlProcessId = crawlProcessId;
        this.crawlProcessStatus = crawlProcessStatus;
    }

    public int getCrawlProcessId() {
        return crawlProcessId;
    }

    public CrawlProcessStatus getCrawlProcessStatus() {
        return crawlProcessStatus;
    }
}
