package be.springboot.pp.databases.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
* As Spring context initialization involves Bean creation for classes annotated with @Component,
* Hibernate context initialization involves Table creation for classes annotated with @Entity if they do not exist
* */
@Getter
@Setter
@Entity // instructs hibernate to map it to a table
@Table(name = "account") // to specify table name (if class name and table name are different)
public class Account {
    @Id // instructs hibernate to map it to a primary key
    private Long id;
    // Java Long - DB BIGINT

    private String name;
    private Double currentBalance;
    private Double totalChargedAmount;
    private String status;
    private Double holdAmount;
}
