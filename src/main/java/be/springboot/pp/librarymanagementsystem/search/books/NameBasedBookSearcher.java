package be.springboot.pp.librarymanagementsystem.search.books;

import be.springboot.pp.librarymanagementsystem.entities.Book;
import be.springboot.pp.librarymanagementsystem.entities.BookCopy;
import be.springboot.pp.librarymanagementsystem.repository.BookCopyRepository;
import be.springboot.pp.librarymanagementsystem.repository.BookRepository;

import java.util.List;

public class NameBasedBookSearcher implements BookSearcher {
    private BookRepository bookRepository;
    private BookCopyRepository bookCopyRepository;
    private final String bookName;

    public NameBasedBookSearcher(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public List<BookCopy> search() {
        Book book = bookRepository.findByName(bookName);
        return null;
    }
}
