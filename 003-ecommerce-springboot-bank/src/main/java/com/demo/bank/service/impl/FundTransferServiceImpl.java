package com.demo.bank.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bank.dto.TransactionDTO;
import com.demo.bank.dto.TransactionResponseDTO;
import com.demo.bank.exceptions.BankAccountNotFoundException;
import com.demo.bank.exceptions.LowBalanceException;
import com.demo.bank.model.Account;
import com.demo.bank.model.Transaction;
import com.demo.bank.repository.TransactionRepository;
import com.demo.bank.service.AccountService;
import com.demo.bank.service.FundTransferService;

/**
 * The Class FundTransferServiceImpl.
 *
 * @author shivam.rai
 */
@Service
public class FundTransferServiceImpl implements FundTransferService {

	/** The transaction repository. */
	@Autowired
	TransactionRepository transactionRepository;

	/** The account service. */
	@Autowired
	AccountService accountService;

	/** The Constant TRANSACTION_TYPE_DEBIT. */
	private final static String TRANSACTION_TYPE_DEBIT = "DEBIT";
	
	/** The Constant TRANSACTION_TYPE_CREDIT. */
	private final static String TRANSACTION_TYPE_CREDIT = "CREDIT";
	
	/** The Constant SELF_ACCOUNT_NUMBER. */
	private final static String SELF_ACCOUNT_NUMBER = "624293972";

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(FundTransferServiceImpl.class);

	/**
	 * Initiate fund transfer.
	 *
	 * @param transactionDTO the transaction DTO
	 * @return the transaction response DTO
	 * @throws BankAccountNotFoundException the bank account not found exception
	 * @throws LowBalanceException the low balance exception
	 */
	@Override
	public TransactionResponseDTO initiateFundTransfer(TransactionDTO transactionDTO) throws BankAccountNotFoundException, LowBalanceException {
		String benificiaryAccountNumber = transactionDTO.getAccountNumber();

		// get beneficiary Account
		Account benificiaryAccount = accountService.getAccountByAccountNumber(benificiaryAccountNumber);
		if (benificiaryAccount == null) {
			logger.info("Bank account Not found with Acc No. : {}", benificiaryAccountNumber);
			throw new BankAccountNotFoundException("Bank account Not found with Acc No. : " + benificiaryAccountNumber);
		}
		logger.info("Bank account found with Acc No. : {}", benificiaryAccountNumber);

		//// get self Account
		Account selfAccount = accountService.getAccountByAccountNumber(SELF_ACCOUNT_NUMBER);
		if (selfAccount == null) {
			logger.info("Bank account Not found with Acc No. : {}", SELF_ACCOUNT_NUMBER);
			throw new BankAccountNotFoundException("Bank account Not found with Acc No. : " + selfAccount);
		}
		logger.info("Bank account found with Acc No. : {}", selfAccount.getAccountNo());
		
		if (benificiaryAccount.getBalance() > transactionDTO.getAmount()) {
			
	
		// beneficiary Transaction
		Transaction transaction = new Transaction(0L, SELF_ACCOUNT_NUMBER, transactionDTO.getAmount() + "",
				getInstantDateAndTime(), TRANSACTION_TYPE_DEBIT,
				"DEBITED FROM ACC NO. " + transactionDTO.getAccountNumber(), benificiaryAccount.getUserId());

		Transaction transactionStatus = transactionRepository.save(transaction);
		logger.info("Saved Transaction for Benificiery");

		// Self Transaction
		Transaction transaction2 = new Transaction(0L, transactionDTO.getAccountNumber(),
				transactionDTO.getAmount() + "", getInstantDateAndTime(), TRANSACTION_TYPE_CREDIT,
				"CREDTED TO ACC NO. " + SELF_ACCOUNT_NUMBER, selfAccount.getUserId());

		//Transaction transactionStatus2 =
				transactionRepository.save(transaction2);
		logger.info("Saved Transaction for Self");

		// update balance
		if (transactionStatus != null) {
			double updatedBalBenificeiry = benificiaryAccount.getBalance() - transactionDTO.getAmount();
			accountService.updateAccountBalanceByAccountNumber(benificiaryAccount.getAccountNo(), updatedBalBenificeiry + "");
			logger.info("Bank account balance updated for Account No. : {}", benificiaryAccount.getAccountNo());
			
			double updatedBalSelf = selfAccount.getBalance() + transactionDTO.getAmount();
			accountService.updateAccountBalanceByAccountNumber(SELF_ACCOUNT_NUMBER, updatedBalSelf + "");
			logger.info("Bank account balance updated for Account No. : {}", selfAccount.getAccountNo());
		} else {
			logger.error("Bank account not balance updated for Account No. : {}", selfAccount.getAccountNo());
			return new TransactionResponseDTO(benificiaryAccountNumber,transactionDTO.getAmount(),"Transaction Failed");
		}

		}else {
			logger.error("Account Balance is low for this transaction. : {}", benificiaryAccount.getBalance());
			throw new LowBalanceException("Account Balance is low for this transaction.");
			
		}
		return new TransactionResponseDTO(benificiaryAccountNumber,transactionDTO.getAmount(),"Transaction Success");
	}

	/**
	 * Gets the instant date and time.
	 *
	 * @return the instant date and time
	 */
	public String getInstantDateAndTime() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String date = df.format(calobj.getTime());
		return date;
	}

}