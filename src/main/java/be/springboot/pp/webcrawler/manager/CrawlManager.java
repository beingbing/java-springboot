package be.springboot.pp.webcrawler.manager;

import be.springboot.pp.webcrawler.db.CrawlDB;
import be.springboot.pp.webcrawler.dto.CrawlResult;
import be.springboot.pp.webcrawler.workers.CrawlWorker;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@Component
public class CrawlManager {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);

    public CrawlResult start(String seedUrl) {
        int crawlProcessId = 0;
        Random random = new Random();
        while (true) {
            crawlProcessId = random.nextInt(1, 100000);
            if (!CrawlDB.hasId(crawlProcessId))
                break;
        }
        CrawlResult crawlResult = new CrawlResult(seedUrl, crawlProcessId);
        threadPool.submit(new FutureTask<>(new CrawlWorker(crawlResult)));
        CrawlDB.pushResult(crawlResult);
        return crawlResult;
    }

    public CrawlResult getResult(int crawlProcessId) {
        return CrawlDB.getResult(crawlProcessId);
    }
}
