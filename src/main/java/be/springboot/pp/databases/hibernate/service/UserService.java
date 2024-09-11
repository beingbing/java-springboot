package be.springboot.pp.databases.hibernate.service;

import be.springboot.pp.databases.hibernate.dao.AccountDao;
import be.springboot.pp.databases.hibernate.dao.UserDao;
import be.springboot.pp.databases.hibernate.entity.Account;
import be.springboot.pp.databases.hibernate.entity.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AccountDao accountDao;

//    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        System.out.println("UserService: init");
        List<User> users = userDao.findByPasswordLike("%pass");

        for (User user : users) {
            log.info("user: {}", user);
        }
        System.out.println("UserService: init: ended");
    }

//    @EventListener(ContextRefreshedEvent.class)
    public void createUsers() {
        System.out.println("createUsers: create initialized");

        List<Account> accounts = accountDao.findAll();

        for (Account account : accounts) {
            User user = new User();
            user.setName(account.getName());
            user.setEmail(account.getName().toLowerCase() + "@example.com");
            user.setCreatedAt(new Date());
            user.setPassword(account.getName().toLowerCase() + "pass");
            user.setUsername(account.getName().toLowerCase());
            user.setAccount(account);
            log.info("createUsers: user: {}", user);
            userDao.save(user);
        }

        System.out.println("createUsers: create: ended");
    }

    @EventListener(ContextRefreshedEvent.class)
    private void createAccount() {
        // fir kabhi
    }
}
