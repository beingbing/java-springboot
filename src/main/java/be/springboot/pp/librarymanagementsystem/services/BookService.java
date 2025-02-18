package be.springboot.pp.librarymanagementsystem.services;

import be.springboot.pp.librarymanagementsystem.dtos.NewBook;
import be.springboot.pp.librarymanagementsystem.entities.Book;
import be.springboot.pp.librarymanagementsystem.repository.BookRepository;

import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    public boolean addBook(NewBook newBookDetails) {
        Book book = bookRepository.findByName(newBookDetails.getName());
        if (book != null)
            throw new IllegalArgumentException("A book by provided details already exist");

        Book newBook = new Book(newBookDetails.getName(), newBookDetails.getAuthorNames(), newBookDetails.getPublicationDate());
        return bookRepository.save(newBook);
    }

    public boolean removeBook(Long bookId) {
        //
        return false;
    }

    public List<Book> getInventory() {
        //
        return null;
    }
}
