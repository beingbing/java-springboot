package be.springboot.pp.databases.hibernate.entity;

import be.springboot.pp.databases.hibernate.enums.ComicBrand;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Getter
@Setter
@ToString
@Entity
@Immutable
@NoArgsConstructor
@Table(name = "comics_brands")
public class ComicsBrands {

    @Id
    @GeneratedValue
    public Long id;

    @Enumerated(EnumType.STRING)
    public ComicBrand name;

    public ComicsBrands(ComicBrand name) {
        this.name = name;
    }
}
