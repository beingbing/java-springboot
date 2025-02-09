package be.springboot.pp.designpattern.behavioral.chainofresponsibility.requestprocessor;

public interface Handler {
    void setNextHandler(Handler nextHandler); // Stores a reference to the next handler.
    void handleRequest(String request);
}
