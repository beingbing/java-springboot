package be.springboot.pp.designpattern.behavioral.iterator.library;

public class BookIterator implements Iterator {
    private Book[] books;
    private int index = 0;

    public BookIterator(Book[] books) {
        this.books = books;
    }

    @Override
    public boolean hasNext() {
        return index < books.length && books[index] != null;
    }

    @Override
    public Object next() {
        return hasNext() ? books[index++] : null;
    }
}
