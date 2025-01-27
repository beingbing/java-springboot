package be.springboot.pp.librarymanagementsystem.book;

/*
* Inheriting BookDetails by BookCopy will be bad. Because inheritance is mainly done to acquire behavior.
* BookDetails mainly contain only properties. But apart from that, inheritance should be discouraged.
* Instead opt for Composition wherever possible.
* */
public class BookCopy {

    private final BookDetails bookDetails;
    private final Long id;


    public BookCopy(BookDetails bookDetails, Long id) {
        this.bookDetails = bookDetails;
        this.id = id;
    }
}
