package be.springboot.pp.librarymanagementsystem.entities;

import java.time.LocalDate;
import java.util.List;

public class Book {
    private Long id;
    private final String name;
    private final List<String> authors;
    private final LocalDate publicationDate;

    public Book(String name, List<String> authors, LocalDate publicationDate) {
        this.name = name;
        this.authors = authors;
        this.publicationDate = publicationDate;
    }

    public String getName() {
        return name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }
}
