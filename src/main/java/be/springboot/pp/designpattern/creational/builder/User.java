package be.springboot.pp.designpattern.creational.builder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String panNumber;
    private final String gstNumber;
    private final char gender;
    private final String contactNumber;
    private final String email;

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.panNumber = builder.panNumber;
        this.gstNumber = builder.gstNumber;
        this.gender = builder.gender;
        this.contactNumber = builder.contactNumber;
        this.email = builder.email;
    }

    public static class Builder {
        private final String firstName;
        private final String lastName;
        private int age;
        private String panNumber;
        private String gstNumber;
        private char gender = 'U';
        private String contactNumber;
        private String email;

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setPanNumber(String panNumber) {
            this.panNumber = panNumber;
            return this;
        }

        public Builder setGstNumber(String gstNumber) {
            this.gstNumber = gstNumber;
            return this;
        }

        public Builder setGender(char gender) {
            this.gender = gender;
            return this;
        }

        public Builder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
