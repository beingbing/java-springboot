package be.springboot.pp.designpattern.behavioral.iterator.user;

import java.util.List;

public class UserListIterator implements UserIterator {
    private List<User> users;
    private int position = 0;

    public UserListIterator(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean hasNext() {
        return position < users.size();
    }

    @Override
    public User next() {
        return hasNext() ? users.get(position++) : null;
    }
}
