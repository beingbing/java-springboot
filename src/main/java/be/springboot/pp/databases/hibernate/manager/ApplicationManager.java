package be.springboot.pp.databases.hibernate.manager;

import be.springboot.pp.databases.hibernate.entity.Address;
import be.springboot.pp.databases.hibernate.entity.Application;
import be.springboot.pp.databases.hibernate.entity.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class ApplicationManager {

    @PersistenceContext
    private EntityManager entityManager;

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void check() {
        System.out.println("ApplicationManager: context initialized");
        // Persistence Context starts due to @Transactional

        Address address = new Address();
        address.setAddress("94B Phase 5, Sector 63");
        address.setCity("Noida");
        address.setState("UP");
        address.setZipCode("131001");
        address.setLongitude(43.0060);
        address.setLatitude(14.7128);
        System.out.println("Address: " + address);

        Payment payment = new Payment();
        payment.setPaymentMode("CC");
        payment.setPaymentStatus("FAILED");
        payment.setDiscountAmount(10.0);
        payment.setDiscountPercentage(null);
        payment.setOriginalAmount(100.0);
        payment.setPaidAmount(70.0);
        payment.setClaimedCouponAmount(20.0);
        payment.setSecurityDeposit(0.0);
        payment.setClaimedCouponCode("WHYCASH");
        payment.setPaymentBankReferenceNumber("6532");
        System.out.println("Payment: " + payment);

        // entityManager.persist(payment);

        Application application = new Application();
        application.setName("Chacha Choudhary");
        application.setStoreName("Property Broker");
        application.setStatus("DRAFT");
        application.setMobile("7657553329");
        application.setOrderDate(new Date());
        application.setAddress(address);
        application.setPaymentList(Collections.singletonList(payment));
        System.out.println("Application: " + application);

        entityManager.persist(application);
        System.out.println("ApplicationManager: context initialized: ended");
    }

//    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void update() {
        System.out.println("ApplicationManager: update");

        List<Application> applicationList = entityManager.createQuery("Select a from Application a", Application.class).getResultList();

        for (Application application : applicationList) {
            System.out.println("Application: " + application);
        }

        System.out.println("ApplicationManager: update: ended");
    }
}
