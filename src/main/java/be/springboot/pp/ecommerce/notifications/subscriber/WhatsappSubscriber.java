package be.springboot.pp.ecommerce.notifications.subscriber;

public class WhatsappSubscriber implements Subscriber{
    private final String whatsappNumber;

    public WhatsappSubscriber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    @Override
    public void notify(String message) {

    }
}
