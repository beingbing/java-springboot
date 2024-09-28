package be.springboot.pp.webcrawler.dto;

public class ResultsFetcherResponse {

    private CrawlResult crawlResult;

    public ResultsFetcherResponse(CrawlResult crawlResult) {
        this.crawlResult = crawlResult;
    }

    public CrawlResult getCrawlResult() {
        return crawlResult;
    }
}
