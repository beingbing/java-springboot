package be.springboot.pp.databases.hibernate.dao;

import be.springboot.pp.databases.hibernate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    List<User> findByPasswordLike(String password);

    // JPQL
    /*
    * by using `@Query`, we instruct JPA to use query provided by us and implement
    * the remaining part of the function by itself.
    * */
//    @Query("select m from User where m.id > :id")
//    List<User> findUsersByIdGreaterThan(Long id);
}
