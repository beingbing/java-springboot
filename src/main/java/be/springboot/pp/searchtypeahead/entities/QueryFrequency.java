package be.springboot.pp.searchtypeahead.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "query_frequency")
public class QueryFrequency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "query")
    private String query;

    @Column(name = "frequency")
    private Long frequency;

    public QueryFrequency(String query, Long frequency) {
        this.query = query;
        this.frequency = frequency;
    }
}
