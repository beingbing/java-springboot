package be.springboot.pp.librarymanagementsystem.search.books;

import be.springboot.pp.librarymanagementsystem.entities.BookCopy;
import be.springboot.pp.librarymanagementsystem.repository.BookCopyRepository;

import java.util.List;

public class AuthorBasedBookSearcher  implements BookSearcher {
    private BookCopyRepository bookCopyRepository;
    private final List<String> authors;

    public AuthorBasedBookSearcher(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public List<BookCopy> search() {
        return bookCopyRepository.findByAuthorName(authors);
    }
}
