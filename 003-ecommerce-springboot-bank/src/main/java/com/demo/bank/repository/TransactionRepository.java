package com.demo.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.bank.model.Transaction;

// TODO: Auto-generated Javadoc
/**
 * The Interface TransactionRepository.
 *
 * @author shivam.rai
 */
public interface TransactionRepository extends JpaRepository<Transaction,Long>{

	/**
	 * Find all transaction by user id.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<Transaction> findAllTransactionByUserId(Long userId);
}
