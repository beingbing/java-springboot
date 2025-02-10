package be.springboot.pp.designpattern.behavioral.chainofresponsibility.firewall;

public class AuthenticationHandler implements FirewallHandler {
    private FirewallHandler nextHandler;

    @Override
    public void setNextHandler(FirewallHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {
        if (!request.isAuthenticated()) {
            System.out.println("🚫 Authentication Required!");
            return;
        }
        System.out.println("✅ Authentication Passed");

//        if (nextHandler != null) {
            nextHandler.handleRequest(request);
//        }
    }
}
