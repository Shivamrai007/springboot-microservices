package com.demo.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.bank.model.Account;

// TODO: Auto-generated Javadoc
/**
 * The Interface AccountRepository.
 *
 * @author shivam.rai
 */
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	/**
	 * Gets the account by user id.
	 *
	 * @param userId the user id
	 * @return the account by user id
	 */
	public Account getAccountByUserId(String userId);
	
	/**
	 * Gets the account by account no.
	 *
	 * @param accountNo the account no
	 * @return the account by account no
	 */
	public Account getAccountByAccountNo(String accountNo);
	
	

}
