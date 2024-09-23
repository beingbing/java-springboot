package be.springboot.pp.springcore.events;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/*
* The events which i am listening in this file are done synchronously, for doing it
* in Asynchronous way, use @Async
* TODO: HW: implement event listening asynchronously.
* */

// Handle the custom event using the @EventListener annotation
@Component
public class BookEventListener {

    @EventListener
    public void handleBookCreatedEvent(BookCreatedEvent event) {
        System.out.println("Handling event for book: " + event.getBookName());
        // Perform additional actions (e.g., send notifications)
    }
}



// another way of listening
class BookEventListener2 implements ApplicationListener<BookCreatedEvent> {

    @Override
    public void onApplicationEvent(BookCreatedEvent event) {
        System.out.println("Handling event for book: " + event.getBookName());
    }
}
