package be.springboot.pp.databases.hibernate.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private Date createdAt;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "likes", joinColumns = {@JoinColumn(name = "quote_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> appreciatorList;

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", author=" + (ObjectUtils.isEmpty(author) ? null : author.getId()) +
                '}';
    }

    public Quote(String title, String description, Date createdAt, User author) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.author = author;
    }
}
