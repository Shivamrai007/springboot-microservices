package com.ecommerce.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
/**
 * @author shivam.rai
 *
 */
@Getter
@Setter
public class OrderItemsDTO {

	@NotEmpty(message = "Please enter product id")
	private String productId;
	
	@NotEmpty(message = "Please enter quantity")
	private double quantity;
}
