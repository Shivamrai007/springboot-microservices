package com.example.config.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.dto.TransactionDTO;
import com.ecommerce.dto.TransactionResponseDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface TransactionClient.
 *
 * @author shivam.rai
 */
//@FeignClient(value = "bank-service", url = "http://localhost:8082/bank/banks")
@FeignClient(name = "http://bank-service/bank/banks")
public interface TransactionClient {

	/**
	 * Initiate fund transfer.
	 *
	 * @param transactionDTO the transaction DTO
	 * @return the transaction response DTO
	 */
	@PostMapping
	public TransactionResponseDTO initiateFundTransfer(@RequestBody TransactionDTO transactionDTO);
}
