package be.springboot.pp.designpattern.behavioral.iterator.user;

public class Tester {

    public static void main(String[] args) {
        UserGroup userGroup = new UserGroup();
        userGroup.addUser("Alice");
        userGroup.addUser("Bob");
        userGroup.addUser("Charlie");

        UserIterator iterator = userGroup.createIterator();

        System.out.println("User List:");
        while (iterator.hasNext()) {
            User user = iterator.next();
            System.out.println("- " + user.getName());
        }
    }
}
