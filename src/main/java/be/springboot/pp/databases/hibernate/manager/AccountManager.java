package be.springboot.pp.databases.hibernate.manager;

import be.springboot.pp.databases.hibernate.entity.Account;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
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
//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void init() {
        // Persistence Context starts due to @Transactional
        System.out.println("AccountManager initialized");

        Account account = new Account();    // NEW/TRANSIENT
        account.setName("Rubab");
        account.setCurrentBalance(45.0);
        account.setTotalChargedAmount(0.3 * account.getCurrentBalance());
        account.setStatus("INACTIVE");
        account.setHoldAmount(0.4);

        entityManager.persist(account);     // NEW/TRANSIENT ===> MANAGED

//        entityManager.detach(account);      // MANAGED ===> DETACHED

        // modifying the detached entity
        modifyAccount(account); // this modification would have been reflected after merge()
        // if we were allowed to manage entity in persistence context
        // similarly if you transition managed => removed and then removed => managed
        // the entity which would have been deleted from table if left in removed will
        // no longer be deleted.

        /*
        * jakarta.persistence.OptimisticLockException: Row was updated or deleted by another
        * transaction (or unsaved-value mapping was incorrect)
        *
        * The issue you're encountering stems from trying to merge an entity that was detached
        * and modified. Hibernate throws an OptimisticLockException because it expects entities
        * to be versioned (via a @Version field) to manage concurrent updates properly. When you
        * detach and then re-merge an entity, Hibernate needs to check whether the row was modified
        * by another transaction in the meantime. Without proper versioning, Hibernate cannot
        * perform this check reliably.
        *
        * Solution -
        * You need to introduce a version field in your entity (Account) to handle optimistic
        * locking properly. This will ensure that Hibernate tracks changes and avoids conflicts
        * when merging detached entities.
        *
        * After effects -
        * even after implementing the said solution, it will not work, as @Transactional didn't
        * permit for us to detach() and merge() the entity manually, all those states are managed
        * by it and has been abstracted out.
        * */
//        entityManager.merge(account);       // DETACHED ===> MANAGED
        // those above changes will be reflected in DB
        // Persistence Context ends and all MANAGED entities will go for .commit()
    }

    private void modifyAccount(Account account) {
        account.setStatus("ACTIVE");
        account.setTotalBalance(10.0);
    }

//    @EventListener(ContextRefreshedEvent.class)
//    @Transactional
    public void context() {
        System.out.println("context initialized");

        List<Account> accountList = entityManager.createQuery("select a from Account a", Account.class).getResultList(); // this is JPQL. it convers resultset to list of the appropriate object
        log.info("accountList: {}", accountList);

        for (Account account : accountList) {
            log.info("account: {}", account);
            log.info("account.getCurrentBalance: {}", account.getCurrentBalance());
            log.info("account.getTotalChargedAmount: {}", account.getTotalChargedAmount());
            log.info("account.getHoldAmount: {}", account.getHoldAmount());
            log.info("account.getStatus: {}", account.getStatus());
            log.info("account.getName: {}", account.getName());
            log.info("account.getId: {}", account.getId());
            log.info("account.getVersion: {}", account.getVersion());

            /*
             * By the concept of Dirty-checking, changes made in the object will be translated and saved in DB row as well.
             * But it will not happen right away, it will happen at the end when the transaction is committed.
             * And, if we remove @Transactional annotation, the changes will not be reflected to DB.
             *
             * This is because, all the object returned by Hibernate are attached to a Transaction-scoped Persistence Context.
             * - The persistence context starts when the transaction begins and ends when the transaction is committed or rolled back.
             * - In this context, once the transaction ends, entities become detached from the persistence context, meaning they are
             * no longer tracked for automatic updates.
             * - `EntityManager` is the interface responsible for managing the persistence context.
             * - Persistence Context ensures that within a single transaction, there is only one instance of a particular entity
             * (with the same primary key). If the same entity is loaded more than once, the persistence context ensures you
             * get the same instance, ensuring consistency.
             * - Entity Lifecycle within a persistence context -
             *   - New/Transient: An entity that has been created but is not yet associated with the persistence context or the database.
             *   - Managed: An entity that is currently managed by the persistence context, meaning it is synchronized with the database.
             *   - Detached: An entity that was once managed by the persistence context but is no longer in that context (e.g., after the transaction ends).
             *   - Removed: An entity that has been marked for deletion but is still in the persistence context until the transaction commits.
             * */
            account.setTotalBalance(account.getCurrentBalance() - account.getTotalChargedAmount() + account.getHoldAmount());

            log.info("account.getTotalBalance: {}", account.getTotalBalance());
            log.info("====================*****====================");
        }

        System.out.println("context completed");
    }

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    private void moreJPA() {
        System.out.println("more jpa");

        Account account = entityManager.find(Account.class, 352); // JPQL, once loaded into object it will be in managed state.
        // by mentioning the class, you hint hibernate for which table to search
        // by mentioning the PK, you hint hibernate for which row to search

        log.info("before detaching and making change, account - {}", account);
        entityManager.detach(account); // in detached state
        // now no changes will be tracked by persistence unit
        account.setTotalBalance(100.0);
        log.info("after detaching and making change, account - {}", account);
        // now, when the transaction will be committed, no changes will get reflected.
        // we normally detach and let changes happen in scenarios where we need to pass object to other functions
        // whom we do not want to give authority to modify the state.

        entityManager.merge(account); // now it is in managed state again
        // now the changes will be reflected if any changes are made in the object

        entityManager.remove(account); // will delete the row from DB when txn will be committed.
        entityManager.persist(account); // it will revert delete action if txn was not committed.
    }
}
