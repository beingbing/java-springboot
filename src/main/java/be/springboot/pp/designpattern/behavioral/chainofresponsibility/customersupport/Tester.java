package be.springboot.pp.designpattern.behavioral.chainofresponsibility.customersupport;

public class Tester {

    public static void main(String[] args) {
        SupportHandler faq = new FAQHandler();
        SupportHandler liveChat = new LiveChatHandler();
        SupportHandler manager = new ManagerHandler();

        // Form the chain
        faq.setNext(liveChat);
        liveChat.setNext(manager);

        // Test Cases
        System.out.println("=== Customer Query: FAQ ===");
        faq.handleRequest("FAQ");

        System.out.println("\n=== Customer Query: Live Chat ===");
        faq.handleRequest("Live Chat");

        System.out.println("\n=== Customer Query: Manager ===");
        faq.handleRequest("Manager");
    }
}
