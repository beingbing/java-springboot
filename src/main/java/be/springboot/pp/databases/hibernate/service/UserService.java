package be.springboot.pp.databases.hibernate.service;

import be.springboot.pp.databases.hibernate.dao.UserDao;
import be.springboot.pp.databases.hibernate.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

//    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        System.out.println("UserService: init");
        List<User> users =  userDao.findByPasswordLike("%pass");

        for (User user : users) {
            log.info("user: {}", user);
        }
        System.out.println("UserService: init: ended");
    }
}
