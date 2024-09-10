package be.springboot.pp.databases.hibernate.manager;

import be.springboot.pp.databases.hibernate.entity.Application;
import be.springboot.pp.databases.hibernate.entity.Quote;
import be.springboot.pp.databases.hibernate.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class QuoteManager {

    @PersistenceContext
    private EntityManager entityManager;

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void createUsers() {
        System.out.println("createUsers: create initialized");

        List<User> users = Arrays.asList(
                new User("Chris Evans", "cevans", "cevans@example.com", "chrispass"),
                new User("Olivia Brown", "obrown", "oliviab@example.com", "oliviapass"),
                new User("David Clark", "dclark", "davidclark@example.com", "davidpass"),
                new User("Sophia Turner", "sturner", "sophiat@example.com", "sophiapass")
        );

        for (User user : users) {
            user.setCreatedAt(new Date());
            log.info("createUsers: user: {}", user);
            entityManager.persist(user);
        }

        System.out.println("createUsers: create: ended");
    }

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    private User createUser() {
        System.out.println("createUser: create initialized");
        User user = new User();
        user.setName("John Doe");
        user.setEmail("TfXO5@example.com");
        user.setCreatedAt(new Date());
        user.setPassword("password");
        user.setUsername("johndoe");
        log.info("createUser: user: {}", user);
        entityManager.persist(user);
        System.out.println("createUser: create: ended");
        return user;
    }

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void createQuotes() {
        System.out.println("QuoteManager: create initialized");

        List<Quote> quotes = Arrays.asList(
                new Quote("Improve effectiveness", "If you know you will fail, then fail fast.", new Date(), entityManager.createQuery("select u from User u where u.username = 'johndoe'", User.class).getSingleResult()),
                new Quote("Perseverance", "Success is not final, failure is not fatal: It is the courage to continue that counts.", new Date(), entityManager.createQuery("select u from User u where u.username = 'slee'", User.class).getSingleResult()),
                new Quote("Learning", "The more that you read, the more things you will know.", new Date(), entityManager.createQuery("select u from User u where u.username = 'dclark'", User.class).getSingleResult()),
                new Quote("Innovation", "The only way to discover the limits of the possible is to go beyond them into the impossible.", new Date(), entityManager.createQuery("select u from User u where u.username = 'cevans'", User.class).getSingleResult()),
                new Quote("Adaptability", "Intelligence is the ability to adapt to change.", new Date(), entityManager.createQuery("select u from User u where u.username = 'emilyd'", User.class).getSingleResult()),
                new Quote("Teamwork", "Coming together is a beginning, staying together is progress, and working together is success.", new Date(), entityManager.createQuery("select u from User u where u.username = 'sturner'", User.class).getSingleResult())
        );

        User author = entityManager.createQuery("select u from User u where u.username = 'johndoe'", User.class).getSingleResult();

        for (Quote quote : quotes) {
            quote.setAuthor(author);
            log.info("QuoteManager: quote added: {}", quote);
            entityManager.persist(quote);
        }

        System.out.println("QuoteManager: create: ended");
    }

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void createQuote() {
        System.out.println("QuoteManager: create initialized");
        Quote quote = new Quote();
        quote.setTitle("Improve effectiveness");
        quote.setDescription("If you know you will fail, then fail fast.");
        quote.setCreatedAt(new Date());
        quote.setAuthor(entityManager.createQuery("select u from User u where u.username = 'johndoe'", User.class).getSingleResult());
        log.info("QuoteManager: quote: {}", quote);
        entityManager.persist(quote);
        System.out.println("QuoteManager: create: ended");
    }

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void getAll() {
        System.out.println("QuoteManager: getAll");

        List<Quote> quotes = entityManager.createQuery("Select q from Quote q", Quote.class).getResultList();

        for (Quote quote : quotes) {
            System.out.println("Quote: " + quote);
            User author = quote.getAuthor();
            log.info("QuoteManager: author: {}", author);
        }

        System.out.println("QuoteManager: getAll: ended");
    }

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void likedBy() {
        System.out.println("QuoteManager: likedBy");
        Quote quote = entityManager.createQuery("select q from Quote q where q.id = 2", Quote.class).getSingleResult();
        quote.setAppreciatorList(entityManager.createQuery("select u from User u where u.id in (6, 52, 53, 4, 5)", User.class).getResultList());
        entityManager.persist(quote);
        System.out.println("QuoteManager: deleteAll: ended");
    }
}
