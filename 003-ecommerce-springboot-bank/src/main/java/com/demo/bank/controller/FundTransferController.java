package com.demo.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bank.dto.TransactionDTO;
import com.demo.bank.dto.TransactionResponseDTO;
import com.demo.bank.exceptions.BankAccountNotFoundException;
import com.demo.bank.exceptions.LowBalanceException;
import com.demo.bank.service.AccountService;
import com.demo.bank.service.FundTransferService;

// TODO: Auto-generated Javadoc
/**
 * The Class FundTransferController.
 *
 * @author shivam.rai
 */
@RestController
@RequestMapping("banks")
@CrossOrigin //enabled for swagger for running on api gateway port.
public class FundTransferController {

	/** The fund transfer service. */
	@Autowired
	FundTransferService fundTransferService;

	/** The account service. */
	@Autowired
	AccountService accountService;

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(FundTransferController.class);
	
	/**
	 * Initiate fund transfer.
	 *
	 * @param transactionDTO the transaction DTO
	 * @return the transaction response DTO
	 * @throws BankAccountNotFoundException the bank account not found exception
	 * @throws LowBalanceException the low balance exception
	 */
	@PostMapping
	public TransactionResponseDTO initiateFundTransfer(@RequestBody TransactionDTO transactionDTO) throws BankAccountNotFoundException, LowBalanceException {
		logger.info("Transaction Initiated...");
		return fundTransferService.initiateFundTransfer(transactionDTO);
	}


}