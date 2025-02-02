package be.springboot.pp.designpattern.behavioral.iterator.user;

import java.util.ArrayList;
import java.util.List;

public class UserGroup implements UserCollection {
    private List<User> users = new ArrayList<>();

    public void addUser(String name) {
        users.add(new User(name));
    }

    @Override
    public UserIterator createIterator() {
        return new UserListIterator(users);
    }
}
