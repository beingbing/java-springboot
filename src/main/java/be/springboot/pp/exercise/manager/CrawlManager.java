package be.springboot.pp.exercise.manager;

import be.springboot.pp.exercise.db.CrawlDB;
import be.springboot.pp.exercise.dto.CrawlResult;
import be.springboot.pp.exercise.workers.CrawlWorker;
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
