package be.springboot.pp.databases.hibernate.manager;

import be.springboot.pp.databases.hibernate.entity.ComicsBrands;
import be.springboot.pp.databases.hibernate.enums.ComicBrand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ComicsManager {

    @PersistenceContext
    EntityManager entityManager;

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void create() {
        System.out.println("ComicsManager: create");
        entityManager.persist(new ComicsBrands(ComicBrand.ARCHIE));
//        entityManager.persist(new ComicsBrands(ComicBrand.DYNAMITE));

        /*
        * below dirty-check didn't get translated to DB table due to
        * @Immutable annotation on table
        * */
        ComicsBrands brand = entityManager.find(ComicsBrands.class, 103);
        System.out.println("brand at 103 is: " + brand);
        brand.setName(ComicBrand.IDW);
        entityManager.persist(brand);
        System.out.println("modified brand at 103 is: " + brand);

        System.out.println("ComicsManager: create: ended");
    }
}
