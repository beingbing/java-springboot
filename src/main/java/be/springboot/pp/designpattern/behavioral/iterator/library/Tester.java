package be.springboot.pp.designpattern.behavioral.iterator.library;

public class Tester {

    public static void main(String[] args) {
        Library library = new Library(5);
        library.addBook("The Catcher in the Rye");
        library.addBook("To Kill a Mockingbird");
        library.addBook("1984");
        library.addBook("Moby Dick");
        library.addBook("Pride and Prejudice");

        Iterator iterator = library.createIterator();

        System.out.println("Books in the library:");
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println("- " + book.getTitle());
        }
    }
}
