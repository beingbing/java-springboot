package be.springboot.pp.designpattern.behavioral.chainofresponsibility.requestprocessor;

public class AuthHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request.contains("123456")) { // If authentication succeeds, it moves to the next handler.
            System.out.println("✅ Authentication Successful");
        } else { // If authentication fails, the request stops.
            System.out.println("🚫 Authentication Failed!");
            return;
        }
        forwardRequest(request);
    }
}
