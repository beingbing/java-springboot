package be.springboot.pp.designpattern.behavioral.chainofresponsibility.firewall;

public interface FirewallHandler {
    void setNextHandler(FirewallHandler nextHandler);
    void handleRequest(Request request);
}
