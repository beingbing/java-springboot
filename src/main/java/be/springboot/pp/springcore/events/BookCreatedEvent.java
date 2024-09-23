package be.springboot.pp.springcore.events;

import org.springframework.context.ApplicationEvent;

// Create a custom event by extending the ApplicationEvent class:
public class BookCreatedEvent extends ApplicationEvent {
    private final String bookName;

    public BookCreatedEvent(Object source, String bookName) {
        super(source);
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }
}
