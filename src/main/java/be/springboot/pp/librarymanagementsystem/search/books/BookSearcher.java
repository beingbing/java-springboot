package be.springboot.pp.librarymanagementsystem.search.books;

import be.springboot.pp.librarymanagementsystem.book.BookCopy;

import java.util.List;

public interface BookSearcher {

    List<BookCopy> search();

}
