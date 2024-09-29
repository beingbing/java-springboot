package be.springboot.pp.databases.hibernate.dao;

import be.springboot.pp.databases.hibernate.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

    @Modifying
    @Query("update Account a set a.currentBalance = a.currentBalance - :amount, a.holdAmount = a.holdAmount + :amount where a.id = :id")
    void deductCustomerBalance(@Param("amount") Double amount, @Param("id") Long id);

    @Modifying
    @Query("update Account a set a.currentBalance = a.currentBalance + :amount, a.totalBalance = a.totalBalance + :amount where a.id = :id")
    void evilAccumulation(@Param("amount") Double amount, @Param("id") Long id);

}
