package be.springboot.pp.designpattern.behavioral.chainofresponsibility.firewall;

import java.util.HashMap;
import java.util.Map;

public class RateLimitingHandler implements FirewallHandler {
    private FirewallHandler nextHandler;
    private static final int REQUEST_LIMIT = 3;
    private static final Map<String, Integer> requestCount = new HashMap<>();

    @Override
    public void setNextHandler(FirewallHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {
        requestCount.put(request.getIp(), requestCount.getOrDefault(request.getIp(), 0) + 1);

        if (requestCount.get(request.getIp()) > REQUEST_LIMIT) {
            System.out.println("🚫 Rate Limit Exceeded for IP: " + request.getIp());
            return;
        }
        System.out.println("✅ Rate Limit Check Passed");

        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
