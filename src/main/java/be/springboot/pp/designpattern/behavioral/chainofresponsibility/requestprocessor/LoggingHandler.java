package be.springboot.pp.designpattern.behavioral.chainofresponsibility.requestprocessor;

// Logs request before passing it.
public class LoggingHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        System.out.println("📝 Logging Request: " + request);
        forwardRequest(request);
    }
}
