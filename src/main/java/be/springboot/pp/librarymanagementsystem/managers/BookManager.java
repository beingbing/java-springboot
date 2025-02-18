package be.springboot.pp.librarymanagementsystem.managers;

import be.springboot.pp.librarymanagementsystem.dtos.NewBook;
import be.springboot.pp.librarymanagementsystem.services.BookService;

public class BookManager {
    private BookService bookService;

    // add a new book to the system
    public boolean addBook(NewBook newBook) {
        // TODO: validations on NewBook DTO data
        return this.bookService.addBook(newBook);
    }

    // list all the books of the system
}
