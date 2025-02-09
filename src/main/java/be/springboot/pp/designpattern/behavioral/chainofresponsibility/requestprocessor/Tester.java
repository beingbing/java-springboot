package be.springboot.pp.designpattern.behavioral.chainofresponsibility.requestprocessor;

public class Tester {

    public static void main(String[] args) {
        // Create Handlers
        Handler auth = new AuthHandler();
        Handler logger = new LoggingHandler();
        Handler dataProcessor = new DataHandler();

        // Chain the Handlers
        auth.setNextHandler(logger);
        logger.setNextHandler(dataProcessor);

        // Test the Chain
        System.out.println("=== Request 1 ===");
        auth.handleRequest("Process Order, token: 123456");

        System.out.println("\n=== Request 2 ===");
        auth.handleRequest("Process Order, token: qwerty");
    }
}
