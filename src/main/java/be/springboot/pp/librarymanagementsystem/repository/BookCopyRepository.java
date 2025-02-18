package be.springboot.pp.librarymanagementsystem.repository;

import be.springboot.pp.librarymanagementsystem.entities.BookCopy;

import java.util.List;

public interface BookCopyRepository {

    List<BookCopy> findByAuthorName(List<String> authors);

    BookCopy findByBookId(Long bookId);

    List<BookCopy> findById(Long id);

    Boolean save(BookCopy bookCopy);
}
