package com.ecommerce.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
/**
 * @author shivam.rai
 *
 */
@Getter
@Setter
public class OrderRequestDTO {
	
	@NotEmpty(message = "Please enter userId")
	private String userId;
	
	@NotEmpty(message = "Please enter account Number")
	private String accountNumber;

	private List<OrderItemsDTO> orderItemsList;
}
