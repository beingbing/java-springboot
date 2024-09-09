package be.springboot.pp.databases.hibernate.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;
import java.util.List;

/*
* Join effective -> id based relationship b/w entities (ex, payment)
* In this case, we definitely has multiple tables
*
* @Embedded -> object composition (ex, address)
* two separate class linked with composition can be representing sub-parts
* of same table
* */

@Getter
@Setter
@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String storeName;

    private Date orderDate;

    private String status;

    private String mobile;

    @Embedded
    private Address address;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", referencedColumnName = "id", nullable = false)
    private List<Payment> paymentList;

    private Date replacementDate;
}
