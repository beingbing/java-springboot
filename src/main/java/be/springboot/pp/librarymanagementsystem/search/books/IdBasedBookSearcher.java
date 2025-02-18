package be.springboot.pp.librarymanagementsystem.search.books;

import be.springboot.pp.librarymanagementsystem.entities.BookCopy;
import be.springboot.pp.librarymanagementsystem.repository.BookCopyRepository;

import java.util.List;

public class IdBasedBookSearcher implements BookSearcher {
    private BookCopyRepository bookCopyRepository;
    private final Long id;

    public IdBasedBookSearcher(Long id) {
        this.id = id;
    }

    @Override
    public List<BookCopy> search() {
        return bookCopyRepository.findById(id);
    }
}
