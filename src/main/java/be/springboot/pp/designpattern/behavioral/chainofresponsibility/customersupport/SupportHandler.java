package be.springboot.pp.designpattern.behavioral.chainofresponsibility.customersupport;

public interface SupportHandler {
    void setNext(SupportHandler nextHandler);
    void handleRequest(String issueType);
}
