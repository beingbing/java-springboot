package be.springboot.pp.springcore.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

// Use the ApplicationEventPublisher to publish the custom event in a service or controller
@Service
public class BookService {

    private final ApplicationEventPublisher eventPublisher;

    public BookService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void createBook(String bookName) {
        // Logic to create a book (e.g., save to database)
        System.out.println("Book created: " + bookName);

        // Publish the event
        BookCreatedEvent event = new BookCreatedEvent(this, bookName);
        eventPublisher.publishEvent(event);
    }
}
