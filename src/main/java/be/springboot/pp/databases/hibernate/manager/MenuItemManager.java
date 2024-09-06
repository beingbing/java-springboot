package be.springboot.pp.databases.hibernate.manager;

import be.springboot.pp.databases.hibernate.entity.MenuItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MenuItemManager {

    @PersistenceContext
    EntityManager entityManager;

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void context() {
        System.out.println("MenuItemManager: context initialized");

        MenuItem rice = new MenuItem(
                "Jeera Rice",
                "Jeera rice or cumin rice is an Indian dish made from a rice and cumin seeds.",
                "Indian",
                4.7f,
                160);
        entityManager.persist(rice);

        MenuItem dal = new MenuItem(
                "dal tadka",
                "Dal tadka is a popular Indian curry made with rice, lentils, and spices.",
                "Indian",
                4.5f,
                120);
        entityManager.persist(dal);

        MenuItem salad = new MenuItem(
                "Salad",
                "Salad is a healthy food that is made from raw vegetables.",
                "Indian",
                3.5f,
                150);
        entityManager.persist(salad);

        MenuItem papad = new MenuItem(
                "Masala Papad",
                "a crispy, flavorful, and popular Indian snack or appetizer made from urad dal or moong dal dough that's seasoned with spices and rolled out by hand",
                "Indian",
                4.3f,
                80);
        entityManager.persist(papad);

        System.out.println("MenuItem: context: ended");
    }

    @EventListener(ContextRefreshedEvent.class)
    public void fetch() {
        System.out.println("MenuItemManager: fetch");
        MenuItem item = entityManager.find(MenuItem.class, 2);
        System.out.println(item);

        System.out.println("MenuItemManager: fetch: ended");
    }
}
