package be.springboot.pp.databases.hibernate.dao;

import be.springboot.pp.databases.hibernate.entity.LibraryMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryMembershipDao extends JpaRepository<LibraryMembership, Long> {}
