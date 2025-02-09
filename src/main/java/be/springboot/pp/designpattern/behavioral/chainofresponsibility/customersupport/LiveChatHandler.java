package be.springboot.pp.designpattern.behavioral.chainofresponsibility.customersupport;

public class LiveChatHandler implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void setNext(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String issueType) {
        System.out.println("request reached 👩‍💻 Live Agent");
        if (issueType.equalsIgnoreCase("Live Chat")) {
            System.out.println("👩‍💻 Live Agent: Resolving the issue.");
        } else {
            if (nextHandler != null) nextHandler.handleRequest(issueType);
        }
    }
}
