package be.springboot.pp.designpattern.behavioral.chainofresponsibility.firewall;

import java.util.Arrays;
import java.util.List;

public class ContentFilterHandler implements FirewallHandler {
    private FirewallHandler nextHandler;
    private static final List<String> BANNED_WORDS = Arrays.asList("DROP TABLE", "HACK", "SELECT * FROM");

    @Override
    public void setNextHandler(FirewallHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {
        for (String word : BANNED_WORDS) {
            if (request.getData().contains(word)) {
                System.out.println("🚫 Content Blocked! Banned phrase found: " + word);
                return;
            }
        }
        System.out.println("✅ Content Check Passed");

        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
