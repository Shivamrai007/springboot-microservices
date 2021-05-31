package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
/**
 * @author shivam.rai
 *
 */
@Getter
@Setter
public class OrderResponseDTO {

	private String orderId;
	private double totalPrice;
	private String message;

}
