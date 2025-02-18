package be.springboot.pp.librarymanagementsystem.repository;

import be.springboot.pp.librarymanagementsystem.entities.Book;

import java.util.List;

public interface BookRepository {

    Book findById(Long bookId);

    Book findByName(String bookName);

    List<Book> findByPrefix(String name);

    Boolean save(Book book);
}
