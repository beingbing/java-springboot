package be.springboot.pp.librarymanagementsystem.user;

public abstract class User {

    private final Long id;
    private final String name;


    protected User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
