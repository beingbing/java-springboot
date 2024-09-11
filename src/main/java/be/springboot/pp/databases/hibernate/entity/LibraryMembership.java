package be.springboot.pp.databases.hibernate.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/*
* An example of unidirectional one-to-one relationship.
* A membership card links to a member, but the member does not directly store the membership details.
* */

@Getter
@Setter
@ToString
@Entity
@Table(name = "library_membership")
public class LibraryMembership {

    @Id
    @GeneratedValue
    private Long id;

    private Date validTill;

    private String status;

    private String type;

    private Date createdAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    private User user;
}
