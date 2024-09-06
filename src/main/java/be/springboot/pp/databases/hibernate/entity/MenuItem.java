package be.springboot.pp.databases.hibernate.entity;

import be.springboot.pp.databases.hibernate.enums.Cuisine;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private float rating;
    private String description;

    @Column(updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;

    @Column(name = "price_in_dollars")
    @ColumnTransformer(read = "price_in_dollars * 80", write = "? / 80")
    private int priceInRupees;

    // when you want to track updated_at of an entry
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", insertable = false, updatable = false)
    @Generated(GenerationTime.ALWAYS)
    private Date updatedAt;

//    @Transient // no need for this, @Formula make the field as a class field only
    @Formula("(select avg(f.rating) from menu_items f)")
    private float avgRating;

    public MenuItem(String name, String description, Cuisine cuisine, float rating, int priceInRupees) {
        this.name = name;
        this.description = description;
        this.cuisine = cuisine;
        this.rating = rating;
        this.priceInRupees = priceInRupees;
    }
}
