package be.springboot.pp.databases.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity // instructs hibernate to map it to a table
@Getter
@Setter
public class Account {
    @Id // instructs hibernate to map it to a primary key
    private Long id;

    private String name;
    private Double currentBalance;
    private Double totalChargedAmount;
    private String status;
    private Double holdAmount;
}
