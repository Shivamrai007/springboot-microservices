package com.demo.bank.service;

import com.demo.bank.model.Account;

// TODO: Auto-generated Javadoc
/**
 * The Interface AccountService.
 *
 * @author shivam.rai
 */
public interface AccountService {

	/**
	 * Gets the account by user id.
	 *
	 * @param userId the user id
	 * @return the account by user id
	 */
	public Account getAccountByUserId(String userId);

	/**
	 * Update account balance by user id.
	 *
	 * @param userId the user id
	 * @param balance the balance
	 * @return the account
	 */
	public Account updateAccountBalanceByUserId(int userId, String balance); 
	
	/**
	 * Update account balance by account number.
	 *
	 * @param accountNumber the account number
	 * @param balance the balance
	 * @return the account
	 */
	public Account updateAccountBalanceByAccountNumber(String accountNumber, String balance);
	
	/**
	 * Gets the account by account number.
	 *
	 * @param accountNo the account no
	 * @return the account by account number
	 */
	public Account getAccountByAccountNumber(String accountNo);
}