package be.springboot.pp.databases.hibernate.manager;

import be.springboot.pp.databases.hibernate.entity.MenuItem;
import be.springboot.pp.databases.hibernate.enums.Cuisine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MenuItemManager {

    @PersistenceContext
    private EntityManager entityManager;

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void context() {
        System.out.println("MenuItemManager: context initialized");

        MenuItem rice = new MenuItem(
                "Jeera Rice",
                "Jeera rice or cumin rice is an Indian dish made from a rice and cumin seeds.",
                Cuisine.INDIAN,
                4.7f,
                160);
        entityManager.persist(rice);

        MenuItem dal = new MenuItem(
                "dal tadka",
                "Dal tadka is a popular Indian curry made with rice, lentils, and spices.",
                Cuisine.INDIAN,
                4.5f,
                120);
        entityManager.persist(dal);

        MenuItem salad = new MenuItem(
                "Salad",
                "Salad is a healthy food that is made from raw vegetables.",
                Cuisine.INDIAN,
                3.5f,
                150);
        entityManager.persist(salad);

        MenuItem papad = new MenuItem(
                "Masala Papad",
                "a crispy, flavorful, and popular Indian snack or appetizer made from urad dal or moong dal dough that's seasoned with spices and rolled out by hand",
                Cuisine.INDIAN,
                4.3f,
                80);
        entityManager.persist(papad);

        System.out.println("MenuItem: context: ended");
    }

//    @EventListener(ContextRefreshedEvent.class)
    public void fetch() {
        System.out.println("MenuItemManager: fetch");
        MenuItem item = entityManager.find(MenuItem.class, 2);
        System.out.println(item);

        System.out.println("MenuItemManager: fetch: ended");
    }

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void create() {
        System.out.println("MenuItemManager: create");
        MenuItem friedRice = new MenuItem(
                "Fried Rice",
                "Fried rice is an Indian delicacy originated in Kolkata when chinese traders and travellers came via silk route and stayed in India",
                Cuisine.CHINESE,
                4.1f,
                180);
        entityManager.persist(friedRice);

        MenuItem butterChicken = new MenuItem(
                "Butter Chicken",
                "chicken is a type of domesticated fowl, typically the species Gallus gallus domesticus",
                Cuisine.MUGHAL,
                4.0f,
                360);
        entityManager.persist(butterChicken);

        System.out.println("MenuItemManager: create: ended");
    }

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void update() {
        System.out.println("MenuItemManager: update");

        // update won't translate as column is set to immutable
        MenuItem item = entityManager.find(MenuItem.class, 52);
        item.setCuisine(Cuisine.INDIAN);
        entityManager.persist(item);
        System.out.println("item 1 is: " + item);

        // PropertyValueException: not-null property references a null or transient value
        // got above error because cuisine column is non-nullable
        MenuItem item2 = new MenuItem();
        entityManager.persist(item2);
        System.out.println("item 2 is: " + item2);

        System.out.println("MenuItemManager: update: ended");
    }
}
