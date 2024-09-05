package be.springboot.pp.databases.hibernate.manager;

import be.springboot.pp.databases.hibernate.entity.Account;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccountManager {

    /*
    * instead of writing and manually creating/managing/closing a EntityManager
    * EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccountPersistenceUnit");
    * EntityManager em = emf.createEntityManager();
    * .
    * .
    * em.close();
    *
    * we can use @PersistenceContext to manage the EntityManager
    *
    * Note:
    * - an EntityManagerFactory creates a context to manage an Entity using EntityManager.
    * By managing Entity we mean, translation of Entity to relational database.
    * - an EntityManagerFactory is created against a Persistence-unit, and a Persistence-unit
    * is defined against a DB connection.
    * - so, all persistence related tasks after establishing a connection are managed by Persistence-unit,
    * and that management is done using EntityManagerFactory.
    * */
    @PersistenceContext
    private EntityManager entityManager;

    /*
    * instead of writing and managing a transaction manually -
    * entityManager.getTransaction().begin();
    * .
    * .
    * entityManager.getTransaction().commit();
    *
    * we can use @Transactional to manage database operations requiring transactions
    * */

//    @PostConstruct
    /*
    * jakarta.persistence.TransactionRequiredException: No EntityManager with actual
    * transaction available for current thread - cannot reliably process 'persist' call
    *
    * was getting above Exception while starting the application, which indicates that a
    * transaction is not being started (Spring's transaction management wasn't fully
    * initialized when it was called), and when you try to persist an entity using
    * entityManager.persist(account);, no transaction is active.
    *
    * This issue occurs because the @Transactional annotation in Spring requires that the
    * method it annotates is invoked via a Spring-managed proxy. In your case, since the
    * @PostConstruct method is being called during bean initialization, it's not wrapped
    * in a transactional proxy, so the transaction isn't started.
    *
    * Solution
    * Move the initialization logic out of @PostConstruct and into a separate method that
    * is called after the application (Spring context) has fully started. You can use
    * @EventListener(ApplicationReadyEvent.class) to ensure that this method is called after the Spring context has been fully initialized
    * and the transaction management is in place.
    * */
    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void init() {
        System.out.println("AccountManager initialized");

        Account account = new Account();
        account.setId(1L);
        account.setName("Samar");
        account.setCurrentBalance(6_57_857.0);
        account.setTotalChargedAmount(0.3*account.getCurrentBalance());
        account.setStatus("ACTIVE");
        account.setHoldAmount(35_000.0);

        entityManager.persist(account);
    }
}
