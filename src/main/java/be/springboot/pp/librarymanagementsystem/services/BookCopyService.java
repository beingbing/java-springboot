package be.springboot.pp.librarymanagementsystem.services;

import be.springboot.pp.librarymanagementsystem.dtos.NewBookCopy;
import be.springboot.pp.librarymanagementsystem.entities.Book;
import be.springboot.pp.librarymanagementsystem.entities.BookCopy;
import be.springboot.pp.librarymanagementsystem.enums.BookStatus;
import be.springboot.pp.librarymanagementsystem.repository.BookCopyRepository;
import be.springboot.pp.librarymanagementsystem.repository.BookRepository;

public class BookCopyService {
    private BookRepository bookRepository;
    private BookCopyRepository bookCopyRepository;

    public boolean addBookCopy(NewBookCopy bookCopy) {
        Book book = bookRepository.findById(bookCopy.getBookId());
        if (book == null)
            throw new IllegalArgumentException("The copy you are searching for isn't present in records");

        BookCopy newBookCopy = new BookCopy(book.getId(), true);
        return bookCopyRepository.save(newBookCopy);
    }

    public BookCopy getBookCopyById(Long bookCopyId) {
        //
        return null;
    }

    public boolean submitBookCopy(Long bookCopyId) {
        //
        return false;
    }

    public boolean convertBookForReference(Long bookCopyId) {
        BookCopy bookCopy = bookCopyRepository.findByBookId(bookCopyId);
        bookCopy.setBookStatus(BookStatus.REFERENCE);
        return bookCopyRepository.save(bookCopy);
    }
}
