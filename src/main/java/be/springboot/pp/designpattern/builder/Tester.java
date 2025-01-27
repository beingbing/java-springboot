package be.springboot.pp.designpattern.builder;

public class Tester {

    public static void main(String[] args) {
        User sam = new User.Builder("sam", "sha").build();
        System.out.println("sam: " + sam);
    }
}
