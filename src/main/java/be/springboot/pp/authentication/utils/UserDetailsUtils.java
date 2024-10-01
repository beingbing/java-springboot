package be.springboot.pp.authentication.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDetailsUtils {

    /*
     * here, when we are integrating DB access in spring-security via JDBC, we have to create tables for users and authorities.
     * but what if, those table names / column names are different from what spring-security expects to be ?
     *
     * In that case we need to provide insert queries using setCreateUserSql() on manager object created above.
     * */
    public static void insertUserDetailsInDb(JdbcUserDetailsManager manager) {
        manager.setCreateUserSql("");
    }

    public static void createUserDetailsInDB(UserDetailsManager manager) {
        List<UserDetails> userDetails = new ArrayList<>();
        userDetails.add(new User("sturner", "sophiapass", Collections.singletonList((GrantedAuthority)() -> "read")));
        userDetails.add(User.withUsername("janesmith").password("password123").authorities("read", "write").build());
        userDetails.add(User.withUsername("johndoe").password("password").roles("ADMIN").build());
        manager.createUser(userDetails.get(0));
        manager.createUser(userDetails.get(1));
        manager.createUser(userDetails.getFirst());
    }
}
