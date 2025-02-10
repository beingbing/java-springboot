package be.springboot.pp.designpattern.behavioral.chainofresponsibility.firewall;

public class IdleHandler implements FirewallHandler {
    @Override
    public void setNextHandler(FirewallHandler nextHandler) {}

    @Override
    public void handleRequest(Request request) {
        System.out.println("All handlers executed successfully");
    }
}
