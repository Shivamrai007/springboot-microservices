
package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.OrderHistoryResponseDTO;
import com.ecommerce.dto.OrderRequestDTO;
import com.ecommerce.dto.OrderResponseDTO;
import com.ecommerce.exceptions.OrderNotFoundException;
import com.ecommerce.exceptions.OrderTransactionException;
import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.exceptions.ProductOutofStockException;
import com.ecommerce.exceptions.UserNotFoundException;
import com.example.config.model.Orders;

// TODO: Auto-generated Javadoc
/**
 * The Interface OrderService.
 *
 * @author shivam.rai
 */
public interface OrderService {

	/**
	 * Save order.
	 *
	 * @param orderRequestDTO the order request DTO
	 * @return the order response DTO
	 * @throws NumberFormatException the number format exception
	 * @throws UserNotFoundException the user not found exception
	 * @throws ProductNotFoundException the product not found exception
	 * @throws OrderTransactionException the order transaction exception
	 * @throws OrderNotFoundException the order not found exception
	 * @throws ProductOutofStockException the product outof stock exception
	 */
	public OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO)
			throws NumberFormatException, UserNotFoundException, ProductNotFoundException, OrderTransactionException, OrderNotFoundException, ProductOutofStockException;

	/**
	 * Order history.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws NumberFormatException the number format exception
	 * @throws UserNotFoundException the user not found exception
	 */
	public List<OrderHistoryResponseDTO> orderHistory(String userId)
			throws NumberFormatException, UserNotFoundException, OrderNotFoundException;

	/**
	 * Gets the order by id.
	 *
	 * @param orderId the order id
	 * @return the order by id
	 * @throws OrderNotFoundException the order not found exception
	 */
	public Orders getOrderById(Long orderId) throws OrderNotFoundException;

	/**
	 * Update orders by orders id.
	 *
	 * @param orderId the order id
	 * @param order the order
	 * @return the orders
	 * @throws OrderNotFoundException the order not found exception
	 */
	public Orders updateOrdersByOrdersId(Long orderId, Orders order) throws OrderNotFoundException;

}