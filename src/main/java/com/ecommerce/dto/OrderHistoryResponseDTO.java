package com.ecommerce.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * @author shivam.rai
 *
 */
@Getter
@Setter
public class OrderHistoryResponseDTO {

	private long orderId;
	private double totalPrice;
	private List<OrderItemsDTO> orderItemsList;

}
