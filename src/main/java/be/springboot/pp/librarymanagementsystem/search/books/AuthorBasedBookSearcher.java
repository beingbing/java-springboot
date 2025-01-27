package be.springboot.pp.librarymanagementsystem.search.books;

import be.springboot.pp.librarymanagementsystem.book.BookCopy;

import java.util.List;

public class AuthorBasedBookSearcher  implements BookSearcher {

    private final List<String> authors;

    public AuthorBasedBookSearcher(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public List<BookCopy> search() {
        return List.of();
    }
}
