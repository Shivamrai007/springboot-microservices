package com.demo.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author shivam.rai
 *
 */
@Entity
@Table(name = "Account")
@Getter
@Setter
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String accountNo;
	private String userId;
	private double balance;

	

}
