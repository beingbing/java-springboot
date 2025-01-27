package be.springboot.pp.librarymanagementsystem.generator;

/*
* It is not a good design to create too many utility classes this way.
* As it prevents the creation of objects in an OO env. But here it is
* ok because this class won't be having any state but we should limit
* making classes like this.
* */

public class IdGenerator {

    private IdGenerator() {}

    public static Long getUniqueId() {
        return 0L;
    }

}
