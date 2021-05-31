package com.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.OrderHistoryResponseDTO;
import com.ecommerce.dto.OrderRequestDTO;
import com.ecommerce.dto.OrderResponseDTO;
import com.ecommerce.dto.TransactionDTO;
import com.ecommerce.exceptions.OrderNotFoundException;
import com.ecommerce.exceptions.OrderTransactionException;
import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.exceptions.ProductOutofStockException;
import com.ecommerce.exceptions.UserNotFoundException;
import com.ecommerce.service.OrderService;
import com.example.config.feignClient.TransactionClient;


// TODO: Auto-generated Javadoc
/**
 * The Class OrderController.
 *
 * @author shivam.rai
 */
@RestController
@RequestMapping("orders")
@CrossOrigin //enabled for swagger for running on api gateway port.
public class OrderController {

	/** The order service. */
	@Autowired
	OrderService orderService;

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(OrderController.class);

	/**
	 * Save order.
	 *
	 * @param orderRequestDTO the order request DTO
	 * @return the response entity
	 * @throws NumberFormatException the number format exception
	 * @throws UserNotFoundException the user not found exception
	 * @throws ProductNotFoundException the product not found exception
	 * @throws OrderTransactionException the order transaction exception
	 * @throws OrderNotFoundException the order not found exception
	 * @throws ProductOutofStockException the product outof stock exception
	 */
	@PostMapping
	public ResponseEntity<OrderResponseDTO> saveOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO)
			throws NumberFormatException, UserNotFoundException, ProductNotFoundException, OrderTransactionException,
			OrderNotFoundException, ProductOutofStockException {
		logger.info("Saving order is initiated...");
		return new ResponseEntity<OrderResponseDTO>(orderService.saveOrder(orderRequestDTO), HttpStatus.CREATED);
	}

	/**
	 * Order history.
	 *
	 * @param userId the user id
	 * @return the response entity
	 * @throws NumberFormatException the number format exception
	 * @throws UserNotFoundException the user not found exception
	 * @throws OrderNotFoundException 
	 */
	@GetMapping
	public ResponseEntity<List<OrderHistoryResponseDTO>> orderHistory(@RequestParam Long userId)
			throws NumberFormatException, UserNotFoundException, OrderNotFoundException {
		logger.info("Fetching order history is intiated...");
		return new ResponseEntity<List<OrderHistoryResponseDTO>>(orderService.orderHistory(String.valueOf(userId)),
				HttpStatus.OK);
	}

}

