package be.springboot.pp.ecommerce.notifications.subscriber;

public interface Subscriber {
    void notify(String message);
}
