package be.springboot.pp.exercise.workers;

import be.springboot.pp.exercise.dto.CrawlResult;
import be.springboot.pp.exercise.enums.CrawlProcessStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class CrawlWorker implements Callable<CrawlResult> {
    private final CrawlResult crawlResult;

    private final int maxDepth;

    public CrawlWorker(CrawlResult crawlResult) {
        this.crawlResult = crawlResult;
        this.maxDepth = 1;
    }

    public CrawlWorker(CrawlResult crawlResult, int maxDepth) {
        this.crawlResult = crawlResult;
        this.maxDepth = maxDepth;
    }

    @Override
    public CrawlResult call() throws Exception {
        Set<String> visitedUrls = new HashSet<>();
        System.out.println("will start crawling now!!");
        int depth = 0;
        crawl(crawlResult, crawlResult.getSeedUrl(), visitedUrls, depth);
        System.out.println("finished crawling");
        crawlResult.setCrawlProcessStatus(CrawlProcessStatus.FINISHED);
        return crawlResult;
    }

    public void crawl(CrawlResult crawlResult, String url, Set<String> visitedUrls, int curDepth) {
        if (visitedUrls.contains(url) || curDepth > maxDepth) return;

        System.out.println("new URL: " + url);
        visitedUrls.add(url);
        crawlResult.addUrl(url);

        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String absoluteLink = link.absUrl("href");
                if (!visitedUrls.contains(absoluteLink) && absoluteLink.startsWith("https://"))
                    crawl(crawlResult, absoluteLink, visitedUrls, curDepth + 1);
            }
            // ...
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("failed to crawl URL: " + url  + " Error: " + e.getMessage());
        }
    }
}
