package com.demo.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shivam.rai
 *
 */
@Entity
@Table(name = "Transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String accountNumber;
	private String amount;
	private String date;
	private String transactionType;
	private String description;
	private String userId;

}