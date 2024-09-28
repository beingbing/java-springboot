package be.springboot.pp.webcrawler.dto;

import be.springboot.pp.webcrawler.enums.CrawlProcessStatus;

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
