package be.springboot.pp.designpattern.behavioral.chainofresponsibility.customersupport;

public class ManagerHandler implements SupportHandler {
    @Override
    public void setNext(SupportHandler nextHandler) {
        // No next handler (last in chain)
    }

    @Override
    public void handleRequest(String issueType) {
        System.out.println("request reached 👨‍💼 Manager");
        System.out.println("👨‍💼 Manager: Handling high-priority issue.");
    }
}
