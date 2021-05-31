package com.demo.bank.service;

import com.demo.bank.dto.TransactionDTO;
import com.demo.bank.dto.TransactionResponseDTO;
import com.demo.bank.exceptions.BankAccountNotFoundException;
import com.demo.bank.exceptions.LowBalanceException;
import com.demo.bank.model.Transaction;

// TODO: Auto-generated Javadoc
/**
 * The Interface FundTransferService.
 *
 * @author shivam.rai
 */
public interface FundTransferService {

	/**
	 * Initiate fund transfer.
	 *
	 * @param transactionDTO the transaction DTO
	 * @return the transaction response DTO
	 * @throws BankAccountNotFoundException the bank account not found exception
	 * @throws LowBalanceException the low balance exception
	 */
	public TransactionResponseDTO initiateFundTransfer(TransactionDTO transactionDTO) throws BankAccountNotFoundException, LowBalanceException;
	
}