package be.springboot.pp.exercise.controller;

import be.springboot.pp.exercise.dto.CrawlResult;
import be.springboot.pp.exercise.dto.CrawlStarterRequest;
import be.springboot.pp.exercise.dto.CrawlStarterResponse;
import be.springboot.pp.exercise.dto.ResultsFetcherResponse;
import be.springboot.pp.exercise.manager.CrawlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crawl")
public class CrawlController {

    @Autowired
    private CrawlManager crawlManager;

    @PostMapping
    public CrawlStarterResponse startCrawl(@RequestBody CrawlStarterRequest crawlStarterRequest) {
        if (crawlStarterRequest.getSeedUrl() == null)
            throw new NullPointerException("Seed URL cannot be null");
        CrawlResult crawlResult = crawlManager.start(crawlStarterRequest.getSeedUrl());
        return new CrawlStarterResponse(crawlResult.getCrawlProcessId(), crawlResult.getCrawlProcessStatus());
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public ResultsFetcherResponse getResults(@RequestParam("id") int crawlProcessId) {
        if (crawlProcessId <= 0)
            throw new IllegalArgumentException("Invalid Crawl Id: " + crawlProcessId);
        CrawlResult crawlResult = crawlManager.getResult(crawlProcessId);
        return new ResultsFetcherResponse(crawlResult);
    }
}
