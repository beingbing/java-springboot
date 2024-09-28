package be.springboot.pp.webcrawler.dto;

import be.springboot.pp.webcrawler.enums.CrawlProcessStatus;

import java.util.ArrayList;
import java.util.List;

public class CrawlResult {

    private final String seedUrl;

    private final int crawlProcessId;

    private CrawlProcessStatus crawlProcessStatus = CrawlProcessStatus.CRAWLING;

    private final List<String> urlsFound;

    public CrawlResult(String seedUrl, int crawlId) {
        this.seedUrl = seedUrl;
        this.crawlProcessId = crawlId;
        this.urlsFound = new ArrayList<>();
    }

    public String getSeedUrl() {
        return seedUrl;
    }

    public int getCrawlProcessId() {
        return crawlProcessId;
    }

    public void setCrawlProcessStatus(CrawlProcessStatus crawlProcessStatus) {
        this.crawlProcessStatus = crawlProcessStatus;
    }

    public CrawlProcessStatus getCrawlProcessStatus() {
        return crawlProcessStatus;
    }

    public void addUrl(String url) {
        urlsFound.add(url);
    }
}
