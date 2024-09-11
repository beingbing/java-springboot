package be.springboot.pp.databases.hibernate.service;

import be.springboot.pp.databases.hibernate.dao.LibraryMembershipDao;
import be.springboot.pp.databases.hibernate.dao.UserDao;
import be.springboot.pp.databases.hibernate.entity.LibraryMembership;
import be.springboot.pp.databases.hibernate.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LibraryMembershipService {

    @Autowired
    private LibraryMembershipDao libraryMembershipDao;

    @Autowired
    private UserDao userDao;

    // @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void createMembershipEntries() {
        System.out.println("LibraryMembershipService: Creating 10 entries");

        // Assuming you have a User entity with some existing data
        List<User> users = userDao.findAll();

        // Ensure there are at least 10 users available
        if (users.size() < 8) {
            System.out.println("Not enough users to create 10 memberships");
            return;
        }

        for (User user : users) {
            LibraryMembership membership = new LibraryMembership();
            membership.setCreatedAt(new Date());
            membership.setValidTill(new Date(System.currentTimeMillis() + (365 * 24 * 60 * 60 * 1000L))); // 1 year from now
            membership.setStatus("ACTIVE");
            membership.setType(user.getId() % 2 == 0 ? "PREMIUM" : "STANDARD");
            membership.setUser(user); // Associate with a user

            libraryMembershipDao.save(membership);
            System.out.println("Created membership for user: " + user.getUsername());
        }

        System.out.println("LibraryMembershipService: Finished creating 10 entries");
    }
}
