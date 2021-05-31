package com.ecommerce.exceptions;

/**
 * @author shivam.rai
 *
 */
public class ProductOutofStockException extends Exception {

	public ProductOutofStockException(String message){
		super(message);
	}

}
