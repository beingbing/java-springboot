package be.springboot.pp.webcrawler.db;

import be.springboot.pp.webcrawler.dto.CrawlResult;

import java.util.HashMap;
import java.util.Map;

public class CrawlDB {

    private static final Map<Integer, CrawlResult> results = new HashMap<>();

    public static void pushResult(CrawlResult crawlResult) {
        results.put(crawlResult.getCrawlProcessId(), crawlResult);
    }

    public static CrawlResult getResult(int crawlProcessId) {
        return results.get(crawlProcessId);
    }

    public static boolean hasId(int crawlProcessId) {
        return results.containsKey(crawlProcessId);
    }
}
