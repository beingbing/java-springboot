package be.springboot.pp.designpattern.behavioral.chainofresponsibility.firewall;

public class Tester {

    public static void main(String[] args) {
        // Create Handlers
        /*
        * We can also put factory-pattern in use to create
        * the instances of all these handlers.
        * */
        FirewallHandler ipCheck = new IPWhitelistHandler();
        FirewallHandler rateLimiter = new RateLimitingHandler();
        FirewallHandler contentFilter = new ContentFilterHandler();
        FirewallHandler authCheck = new AuthenticationHandler();
        FirewallHandler idleConfirmation = new IdleHandler();

        // Form the chain: IP -> Rate Limit -> Content Filter -> Authentication
        ipCheck.setNextHandler(rateLimiter);
        rateLimiter.setNextHandler(contentFilter);
        contentFilter.setNextHandler(authCheck);
        authCheck.setNextHandler(idleConfirmation);

        // Test Cases
        System.out.println("\n=== Request 1: Valid Request ===");
        ipCheck.handleRequest(new Request("192.168.1.1", "Hello, World!", true));

        System.out.println("\n=== Request 2: Blocked by IP Check ===");
        ipCheck.handleRequest(new Request("203.0.113.5", "Hello!", true));

        System.out.println("\n=== Request 3: Blocked by Content Filter ===");
        ipCheck.handleRequest(new Request("192.168.1.1", "HACK THIS SITE", true));

        System.out.println("\n=== Request 4: Blocked by Authentication ===");
        ipCheck.handleRequest(new Request("192.168.1.1", "Hello, World!", false));
    }
}
