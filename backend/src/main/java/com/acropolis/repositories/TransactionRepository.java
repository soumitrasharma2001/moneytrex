package com.acropolis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acropolis.entity.Transaction;
import com.acropolis.entity.Users;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByUser(Users ob);

	@Query("SELECT t FROM Transaction t WHERE MONTH(t.entrydate) = :month AND YEAR(t.entrydate) = :year AND t.user = :user")
	List<Transaction> findByMonthANDYear( int month,int year, Users user);

	@Query("SELECT t FROM Transaction t WHERE MONTH(t.entrydate) = :month AND YEAR(t.entrydate) = :year AND t.type = :type AND t.user = :user")
	List<Transaction> findByMonthANDYearANDType( int month,int year,String type,Users user);
	                                            
	@Query(value = "SELECT SUM(amount) FROM transaction " +
            "WHERE type = 'income' AND entrydate >= CURDATE() - INTERVAL 30 DAY "
            + "AND customerid= :userid", 
    nativeQuery = true)
	Float getIncomeOfLast30Days(int userid);
	@Query(value = "SELECT SUM(amount) FROM transaction " +
			"WHERE type = 'expense' AND entrydate >= CURDATE() - INTERVAL 30 DAY "
			+ "AND customerid= :userid", 
			nativeQuery = true)
	Float getExpenseOfLast30Days(int userid);
	
}
