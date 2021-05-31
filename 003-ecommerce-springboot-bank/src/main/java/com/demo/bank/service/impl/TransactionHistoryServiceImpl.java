package com.demo.bank.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bank.model.Transaction;
import com.demo.bank.repository.TransactionRepository;
import com.demo.bank.service.TransactionHistoryService;


// TODO: Auto-generated Javadoc
/**
 * The Class TransactionHistoryServiceImpl.
 *
 * @author shivam.rai
 */
@Service
@Transactional
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	/** The transaction repository. */
	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * Gets the all transactions by user id.
	 *
	 * @param userId the user id
	 * @return the all transactions by user id
	 */
	public List<Transaction> getAllTransactionsByUserId(Long userId) {
		return transactionRepository.findAllTransactionByUserId(userId);
	}
}