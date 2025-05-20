package be.springboot.pp.ecommerce.notifications.publisher;

import be.springboot.pp.ecommerce.notifications.subscriber.Subscriber;

public interface Publisher {
    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void notifyAll(String message);
}
