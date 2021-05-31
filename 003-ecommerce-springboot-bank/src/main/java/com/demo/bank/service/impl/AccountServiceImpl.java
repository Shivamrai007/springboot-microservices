package com.demo.bank.service.impl;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bank.model.Account;
import com.demo.bank.repository.AccountRepository;
import com.demo.bank.service.AccountService;


/**
 * @author shivam.rai
 *
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	 private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	 public Account getAccountByUserId(String userId) {
		return accountRepository.getAccountByUserId(userId);
	}

	/**
	 * Update account balance by user id.
	 *
	 * @param userId the user id
	 * @param balance the balance
	 * @return the account
	 */
	@Override
	public Account updateAccountBalanceByUserId(int userId, String balance) {
		
		Account account = getAccountByUserId(userId+"");
		account.setBalance(Double.parseDouble(balance));
		return accountRepository.save(account);
	}
	
	public Account updateAccountBalanceByAccountNumber(String accountNumber, String balance) {
		
		Account account = getAccountByAccountNumber(accountNumber);
		
		if (account == null) {
			 logger.info("Account Not Found with this Account Number");
		}
		
		account.setBalance(Double.parseDouble(balance));
		return accountRepository.save(account);
	}
	
	public Account getAccountByAccountNumber(String accountNo) {
		return accountRepository.getAccountByAccountNo(accountNo);
	}

}