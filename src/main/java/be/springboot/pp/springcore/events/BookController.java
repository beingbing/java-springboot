package be.springboot.pp.springcore.events;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Use a controller to trigger the book creation and publish the event
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public String createBook(@RequestParam String bookName) {
        bookService.createBook(bookName);
        return "Book created: " + bookName;
    }
}
