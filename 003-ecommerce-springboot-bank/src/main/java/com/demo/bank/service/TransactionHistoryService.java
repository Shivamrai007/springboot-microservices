package com.demo.bank.service;

import java.util.List;

import com.demo.bank.model.Transaction;

// TODO: Auto-generated Javadoc
/**
 * The Interface TransactionHistoryService.
 *
 * @author shivam.rai
 */
public interface TransactionHistoryService {

	/**
	 * Gets the all transactions by user id.
	 *
	 * @param userId the user id
	 * @return the all transactions by user id
	 */
	public List<Transaction> getAllTransactionsByUserId(Long userId);
}