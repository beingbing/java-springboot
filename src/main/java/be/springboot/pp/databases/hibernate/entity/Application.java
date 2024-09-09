package be.springboot.pp.databases.hibernate.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
import lombok.ToString;

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
@ToString
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

    /*
    * Here, object of application encapsulates address within it hence life-cycle of address object is bound
    * to application object. Therefore, if application object is removed then address object will be also removed.
    *
    * Here, @Embedded annotation is used to map address object to the application object hence address got bound
    * but it is possible to remove wrapper object from memory and yet still keep encapsulated object alive using joins.
    *
    * This is what we did with paymentList field.
    * */
    @Embedded
    private Address address;

    /*
    * If application table also had columns by name store_address, store_city, store_state, store_zip_code,
    * store_latitude, store_longitude, then we can use @AttributeOverrides to map them to the class fields.
    * `name` is class field name and `column` is table column name.
    * */
//    @AttributeOverrides({
//            @AttributeOverride(name = "address", column = @Column(name = "store_address")),
//            @AttributeOverride(name = "city", column = @Column(name = "store_city")),
//            @AttributeOverride(name = "state", column = @Column(name = "store_state")),
//            @AttributeOverride(name = "zipCode", column = @Column(name = "store_zip_code")),
//            @AttributeOverride(name = "latitude", column = @Column(name = "store_latitude")),
//            @AttributeOverride(name = "longitude", column = @Column(name = "store_longitude"))
//    })
//    @Embedded
//    private Address storeAddress;

    /*
    * cascade-all: ensures all payments in below listed gets stored/updated in payments table.
    * `all` represent all types of actions, hence we also have `insert`, `update`, etc, too.
    *
    * orphanRemoval: if an application object is removed all associated payment object will be removed as well.
    * */
    // mappedBy key makes this relation bi-directional
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> paymentList;
    /*
    * difference between lazy and eager:
    * eager: select query to application table will be made having a join to payments table
    * lazy: only select query to application table without any join clause, payment table query
    * will be made when payment object will be needed
    * */

    private Date replacementDate;
}
