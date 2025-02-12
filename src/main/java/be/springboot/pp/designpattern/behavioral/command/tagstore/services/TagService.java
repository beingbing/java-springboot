package be.springboot.pp.designpattern.behavioral.command.tagstore.services;

import be.springboot.pp.designpattern.behavioral.command.tagstore.db.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TagService {

    public void delete(String name) {
        List<String> tags = new ArrayList<>(Store.getTags());
        for (String tag : tags) if (name.equals(tag)) Store.delete(tag);
    }

    public void delete(Pattern pattern) {
        List<String> tags = new ArrayList<>(Store.getTags());
        for (String tag : tags) if (pattern.matcher(tag).matches()) Store.delete(tag);
    }

    public void insert(String name) {
        //
    }

    public void update(String name) {
        //
    }
}
