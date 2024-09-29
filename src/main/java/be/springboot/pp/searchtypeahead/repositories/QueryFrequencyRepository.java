package be.springboot.pp.searchtypeahead.repositories;

import be.springboot.pp.searchtypeahead.entities.QueryFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryFrequencyRepository extends JpaRepository<QueryFrequency, Long> {
    QueryFrequency findByQuery(String query);
}
