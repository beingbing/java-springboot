package be.springboot.pp.designpattern.behavioral.command.tagstore.db;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static List<String> tags = new ArrayList<>() {
        {
            add("knapsack");
            add("maths");
            add("mathematics");
            add("sieve");
            add("runtime error");
        }
    };

    public static List<String> getTags() {
        return tags;
    }

    public static void delete(String tag) {
        System.out.println("Store: delete: tag: " + tag);
        tags.remove(tag);
    }

    public static void insert(String tag) {
        System.out.println("Store: insert: tag: " + tag);
        tags.add(tag);
    }
}
