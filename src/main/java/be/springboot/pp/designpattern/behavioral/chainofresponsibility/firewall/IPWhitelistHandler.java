package be.springboot.pp.designpattern.behavioral.chainofresponsibility.firewall;

import java.util.HashSet;
import java.util.Set;

public class IPWhitelistHandler implements FirewallHandler {
    private FirewallHandler nextHandler;
    private static final Set<String> ALLOWED_IPS = new HashSet<>();

    static {
        ALLOWED_IPS.add("192.168.1.1");
        ALLOWED_IPS.add("10.0.0.2");
    }

    @Override
    public void setNextHandler(FirewallHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {
        if (!ALLOWED_IPS.contains(request.getIp())) {
            System.out.println("🚫 Access Denied! IP not in whitelist: " + request.getIp());
            return;
        }
        System.out.println("✅ IP Check Passed");

        // we kept this null-check on each handler implementation to prevent last handler
        // in the chain from invoking the next handler. To Preventing null-exception in last
        // handler we put a null check in all handlers. Instead, we can create an idle-handler
        // which will be our last handler and do not write any next-handler execution logic in
        // that. This way, we can do away with null-checks and prevent the last handler null-exception
        // as well.
//        if (nextHandler != null) {
            nextHandler.handleRequest(request);
//        }
    }
}
