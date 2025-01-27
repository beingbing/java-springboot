package be.springboot.pp.librarymanagementsystem.search.books;

import be.springboot.pp.librarymanagementsystem.book.BookCopy;

import java.util.List;

public class IdBasedBookSearcher implements BookSearcher {

    private final Long id;

    public IdBasedBookSearcher(Long id) {
        this.id = id;
    }

    @Override
    public List<BookCopy> search() {
        return List.of();
    }
}
