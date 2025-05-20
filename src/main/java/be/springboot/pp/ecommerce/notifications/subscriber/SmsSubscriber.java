package be.springboot.pp.ecommerce.notifications.subscriber;

public class SmsSubscriber implements Subscriber {
    private final String phoneNumber;

    public SmsSubscriber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notify(String message) {

    }
}
