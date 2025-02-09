package be.springboot.pp.designpattern.behavioral.chainofresponsibility.customersupport;

public class FAQHandler implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void setNext(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String issueType) {
        System.out.println("request reached 🤖 FAQ Bot");
        if (issueType.equalsIgnoreCase("FAQ")) {
            System.out.println("🤖 FAQ Bot: Answering common question.");
        } else {
            if (nextHandler != null) nextHandler.handleRequest(issueType);
        }
    }
}
