package com.demo.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author shivam.rai
 *
 */
@Entity
@Table(name = "User")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Enter Username")
	private String username;

	@NotEmpty(message = "Enter Password")
	private String password;
	private String phoneNo;
	private String emailId;

	
}