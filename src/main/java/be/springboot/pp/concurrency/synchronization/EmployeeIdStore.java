package be.springboot.pp.concurrency.synchronization;

import java.util.HashMap;
import java.util.Map;

/*
* A class is a singleton class if it ensures that in runtime environment
* only one instance of that class will exist.
* So, if other class need to use this class, they will have to use already
* existing instance, making new instance as per whims is not possible.
*
* */
public class EmployeeIdStore {

    private final Map<String, String> employees;

    private static EmployeeIdStore INSTANCE = null;

    private EmployeeIdStore() {
        this.employees = new HashMap<>();

        // read DB and populate below hash-map
        this.employees.put("101", "Samar");
        this.employees.put("102", "Maheen");
        this.employees.put("103", "Rubab");
    }

    // instance creation needs to be thread-safe to be able to
    // have singleton feature implemented correctly
    // TODO
    public static EmployeeIdStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmployeeIdStore();
        }
        return INSTANCE;
    }
}
