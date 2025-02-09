package be.springboot.pp.designpattern.behavioral.chainofresponsibility.requestprocessor;

public abstract class AbstractHandler implements Handler {
    protected Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // Provides a utility method to pass the request to next handler
    protected void forwardRequest(String request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
