package be.springboot.pp.librarymanagementsystem.entities;

import be.springboot.pp.librarymanagementsystem.enums.BookStatus;

/*
* Inheriting Book by BookCopy will be bad. Because inheritance is mainly done to acquire behavior.
* Book mainly contain only properties. But apart from that, inheritance should be discouraged.
* Instead opt for Composition wherever possible.
* */
public class BookCopy {
    private final Long id = 0L;
    private final Long bookId;
    private BookStatus bookStatus;

    public BookCopy(Long bookId, boolean isAvailable) {
        this.bookId = bookId;
        this.bookStatus = BookStatus.AVAILABLE;
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}
