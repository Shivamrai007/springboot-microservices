package com.demo.bank.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author shivam.rai
 *
 */
@Getter
@Setter
public class TransactionDTO {

	@NotEmpty(message = "Enter Valid Account Number")
	private String accountNumber;

	@NotEmpty(message = "Enter Amount")
	private double amount;
	
}