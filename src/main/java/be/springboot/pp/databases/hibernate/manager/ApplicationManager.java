package be.springboot.pp.databases.hibernate.manager;

import be.springboot.pp.databases.hibernate.entity.Address;
import be.springboot.pp.databases.hibernate.entity.Application;
import be.springboot.pp.databases.hibernate.entity.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.Collections;
import java.util.Date;

public class ApplicationManager {

    @PersistenceContext
    private EntityManager entityManager;

    // @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void check() {
        System.out.println("ApplicationManager: context initialized");
        // Persistence Context starts due to @Transactional

        Address address = new Address();
        address.setAddress("123 Main Street");
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("10001");
        address.setLongitude(-74.0060);
        address.setLatitude(40.7128);

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

        Application application = new Application();
        application.setName("Joe Smith");
        application.setStoreName("Smith store");
        application.setStatus("DRAFT");
        application.setMobile("1234567890");
        application.setOrderDate(new Date("2022-01-01"));
        application.setAddress(address);
        application.setPaymentList(Collections.singletonList(payment));

        entityManager.persist(application);
        System.out.println("ApplicationManager: context initialized: ended");
    }
}
