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

@Component
public class ApplicationManager {

    @PersistenceContext
    private EntityManager entityManager;

    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void check() {
        System.out.println("ApplicationManager: context initialized");
        // Persistence Context starts due to @Transactional

        Address address = new Address();
        address.setAddress("122B Baker Street");
        address.setCity("London");
        address.setState("London");
        address.setZipCode("11001");
        address.setLongitude(-17.0060);
        address.setLatitude(32.7128);
        System.out.println("Address: " + address);

        Payment payment = new Payment();
        payment.setPaymentMode("CC");
        payment.setPaymentStatus("PAID");
        payment.setDiscountAmount(10.0);
        payment.setDiscountPercentage(null);
        payment.setOriginalAmount(100.0);
        payment.setPaidAmount(70.0);
        payment.setClaimedCouponAmount(20.0);
        payment.setSecurityDeposit(0.0);
        payment.setClaimedCouponCode("ABC123");
        payment.setPaymentBankReferenceNumber("4587");
        System.out.println("Payment: " + payment);

        // entityManager.persist(payment);

        Application application = new Application();
        application.setName("Sherlock Holmes");
        application.setStoreName("Private Investigation");
        application.setStatus("DRAFT");
        application.setMobile("1278903456");
        application.setOrderDate(new Date());
        application.setAddress(address);
        application.setPaymentList(Collections.singletonList(payment));
        System.out.println("Application: " + application);

        entityManager.persist(application);
        System.out.println("ApplicationManager: context initialized: ended");
    }
}
