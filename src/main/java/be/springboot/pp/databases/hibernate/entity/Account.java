package be.springboot.pp.databases.hibernate.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
* As Spring context initialization involves Bean creation for classes annotated with @Component,
* Hibernate context initialization involves Table creation for classes annotated with @Entity if they do not exist
* */
@Getter
@Setter
@ToString
@Entity // instructs hibernate to map it to a table
@Table(name = "account") // to specify table name (if class name and table name are different)
public class Account {
    @Id // instructs hibernate to map it to a primary key
    @GeneratedValue // creates a sequence table in DB for incrementing and getting next value of primary key, by default it increments by 50.
    private Long id;
    // Java Long - DB BIGINT

    private String name;
    private Double currentBalance;
    private Double totalChargedAmount;
    private String status;
    private Double holdAmount;
    private Double totalBalance;
    // adding a new column will automatically have Hibernate run below query -
    /*
    * alter table account add column total_balance float(53)
    * */

    // Add version for optimistic locking
    @Version
    private int version;  // This will be used by Hibernate to detect conflicting updates
    // This field will automatically increment whenever the entity is updated, allowing Hibernate to detect concurrent modifications.

    @OneToOne(mappedBy = "account")
    private User user;
}
