package be.springboot.pp.ecommerce;

public interface Permission {
    boolean isPermitted();
}
/*
* Q. Different categories of users should have different permissions.
* A. Approach 1:
* Create a User class, and let different types of users inherit from it.
*
* Drawback:
* - we need to create a new class for each combination of permissions.
*
* Approach 2:
* Decouple permissions from users.
* - keep flat user types, and dynamically assign permissions.
*
* To translate it on DB:
* - we can have a user table
* - we can have a permissions table
* - we can have a user_permissions table keeping all the mappings.
* */