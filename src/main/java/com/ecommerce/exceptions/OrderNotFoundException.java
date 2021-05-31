package com.ecommerce.exceptions;

/**
 * @author shivam.rai
 *
 */
public class OrderNotFoundException extends Exception {

	public OrderNotFoundException(String message){
		super(message);
	}

}
