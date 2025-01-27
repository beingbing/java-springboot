package be.springboot.pp.librarymanagementsystem.search.books;

import be.springboot.pp.librarymanagementsystem.book.BookCopy;

import java.util.List;

public class NameBasedBookSearcher implements BookSearcher {

    private final String bookName;

    public NameBasedBookSearcher(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public List<BookCopy> search() {
        return List.of();
    }
}
