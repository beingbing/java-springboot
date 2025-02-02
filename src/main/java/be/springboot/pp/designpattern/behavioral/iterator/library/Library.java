package be.springboot.pp.designpattern.behavioral.iterator.library;

public class Library implements Iterable {
    private Book[] books;
    private int count = 0;

    public Library(int size) {
        books = new Book[size];
    }

    public void addBook(String title) {
        if (count < books.length) {
            books[count++] = new Book(title);
        }
    }

    @Override
    public Iterator createIterator() {
        return new BookIterator(books);
    }
}
