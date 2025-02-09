package be.springboot.pp.designpattern.behavioral.chainofresponsibility.requestprocessor;

public class DataHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        System.out.println("📊 Processing Data: " + request);
    }
}