package com.ecommerce.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ecommerce.dto.OrderHistoryResponseDTO;
import com.ecommerce.dto.OrderItemsDTO;
import com.ecommerce.dto.OrderRequestDTO;
import com.ecommerce.dto.OrderResponseDTO;
import com.ecommerce.dto.TransactionDTO;
import com.ecommerce.dto.TransactionResponseDTO;
import com.ecommerce.exceptions.OrderNotFoundException;
import com.ecommerce.exceptions.OrderTransactionException;
import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.exceptions.ProductOutofStockException;
import com.ecommerce.exceptions.UserNotFoundException;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;
import com.example.config.feignClient.TransactionClient;
import com.example.config.model.OrderItems;
import com.example.config.model.Orders;
import com.example.config.model.Product;
import com.example.config.repository.OrderRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderServiceImpl.
 *
 * @author shivam.rai
 */
@Service
public class OrderServiceImpl implements OrderService {

	/** The order repository. */
	@Autowired
	OrderRepository orderRepository;

	/** The user service. */
	@Autowired
	UserService userService;

	/** The product service. */
	@Autowired
	ProductService productService;

	/** The transaction client. */
	@Autowired
	TransactionClient transactionClient;

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	/**
	 * Save order.
	 *
	 * @param orderRequestDTO the order request DTO
	 * @return the order response DTO
	 * @throws NumberFormatException      the number format exception
	 * @throws UserNotFoundException      the user not found exception
	 * @throws ProductNotFoundException   the product not found exception
	 * @throws OrderTransactionException  the order transaction exception
	 * @throws OrderNotFoundException     the order not found exception
	 * @throws ProductOutofStockException the product outof stock exception
	 */
	public OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO)
			throws NumberFormatException, UserNotFoundException, ProductNotFoundException, OrderTransactionException,
			OrderNotFoundException, ProductOutofStockException {

		userService.getUserByUserId(Long.parseLong(orderRequestDTO.getUserId()));
		logger.info("User found with userId : {}", orderRequestDTO.getUserId());
		Orders order = new Orders();
		BeanUtils.copyProperties(orderRequestDTO, order);
		List<OrderItemsDTO> orderItemsDTOList = orderRequestDTO.getOrderItemsList();
		List<OrderItems> orderItemsList = new ArrayList<OrderItems>();
		for (OrderItemsDTO orderItemsDTO : orderItemsDTOList) {
			OrderItems orderItems = new OrderItems();

			orderItems.setQuantity(orderItemsDTO.getQuantity());
			orderItems.setProductId(orderItemsDTO.getProductId());

			Product product = productService.getProductById(Long.parseLong(orderItemsDTO.getProductId()));
			orderItems.setPrice(Double.parseDouble(product.getPrice()));
			orderItemsList.add(orderItems);
		}

		order.setOrderItemsList(orderItemsList);

		// Date
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		order.setDate(currentDate);
		order.setTime(currentTime);
		order.setPaymentStatus("WAIT");
		// Price
		double totalPrice = orderItemsList.stream().mapToDouble(x -> x.getPrice() * x.getQuantity()).sum();
		order.setTotalPrice(totalPrice);

		// update product quantity if order saved successfully

		List<OrderItems> orderItemsList2 = order.getOrderItemsList();
		for (OrderItems orderItems2 : orderItemsList2) {
			Long productId = Long.parseLong(orderItems2.getProductId());
			Product product = productService.getProductById(productId);
			double Availablequantity = Double.parseDouble(product.getQuantity());

			if (orderItems2.getQuantity() <= 0) {
				throw new OrderTransactionException("Product Quantity should be greater than zero");
			}

			if (Availablequantity > orderItems2.getQuantity()) {
				product.setQuantity((Availablequantity - orderItems2.getQuantity()) + "");
				productService.updateProductByProductId(productId, product);
			} else {
				logger.info("Product is out of Stock : {}");
				throw new ProductOutofStockException("Product is out of Stock");
			}

		}

		// save order
		Orders order2 = orderRepository.save(order);
		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		if (!ObjectUtils.isEmpty(order2)) {
			logger.info("Order saved with order id : {}", order2.getOrderId());

			orderResponseDTO.setMessage("Order saved Successfully");
			orderResponseDTO.setOrderId(order2.getOrderId() + "");
			orderResponseDTO.setTotalPrice(order2.getTotalPrice());

			logger.info("Initiating payment gateway...");
			TransactionDTO transactionDTO = new TransactionDTO();
			transactionDTO.setAccountNumber(orderRequestDTO.getAccountNumber());
			transactionDTO.setAmount(order2.getTotalPrice());
			TransactionResponseDTO transactionResponseDTO = transactionClient.initiateFundTransfer(transactionDTO);

			logger.info("Transaction running with Acc No. {}", transactionResponseDTO.getAccountNumber());
			logger.info("Transaction amount {} ", transactionResponseDTO.getAmount());
			logger.info("Message from Bank Service : {}", transactionResponseDTO.getMessage());

			if (transactionResponseDTO.getMessage().equals("Transaction Success")) {

				// Orders orders3 = getOrderById(order2.getOrderId());
				order2.setPaymentStatus("Payment Successfull");
				updateOrdersByOrdersId(order2.getOrderId(), order2);
			}

		} else {
			logger.error("Order Failed with order id : { }", order2.getOrderId());
			throw new OrderTransactionException("Order Failed with order id : " + order2.getOrderId());
		}

		return orderResponseDTO;
	}

	/**
	 * Order history.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws NumberFormatException  the number format exception
	 * @throws UserNotFoundException  the user not found exception
	 * @throws OrderNotFoundException
	 */
	public List<OrderHistoryResponseDTO> orderHistory(String userId)
			throws NumberFormatException, UserNotFoundException, OrderNotFoundException {

		userService.getUserByUserId(Long.parseLong(userId));
		logger.info("User found with userId : {}", userId);

		List<Orders> orders = orderRepository.findOrderByUserId(userId);
		logger.info("Order found with userId : {}", userId);

		List<OrderHistoryResponseDTO> orderHistoryResponseDTOList = new ArrayList<>();

		for (Orders orders2 : orders) {
			OrderHistoryResponseDTO orderHistoryResponseDTO = new OrderHistoryResponseDTO();
			orderHistoryResponseDTO.setOrderId(orders2.getOrderId());
			orderHistoryResponseDTO.setTotalPrice(orders2.getTotalPrice());

			List<OrderItems> orderItemsList = orders2.getOrderItemsList();
			List<OrderItemsDTO> orderItemsDTOList = new ArrayList<OrderItemsDTO>();

			for (OrderItems orderItems : orderItemsList) {
				OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
				BeanUtils.copyProperties(orderItems, orderItemsDTO);
				orderItemsDTOList.add(orderItemsDTO);
			}

			orderHistoryResponseDTO.setOrderItemsList(orderItemsDTOList);

			orderHistoryResponseDTOList.add(orderHistoryResponseDTO);
		}

		if (orderHistoryResponseDTOList.isEmpty()) {
			throw new OrderNotFoundException("No Orders Found");
		}
		return orderHistoryResponseDTOList;

	}

	/**
	 * Gets the order by id.
	 *
	 * @param orderId the order id
	 * @return the order by id
	 * @throws OrderNotFoundException the order not found exception
	 */
	public Orders getOrderById(Long orderId) throws OrderNotFoundException {
		Optional<Orders> orders = orderRepository.findById(orderId);

		if (orders.isPresent()) {
			logger.info("Order found with order Id : {}", orderId);
			return orders.get();
		} else {
			logger.error("Order not Found with order Id : {}", orderId);
			throw new OrderNotFoundException("Order not Found with order Id " + orderId);
		}
	}

	/**
	 * Update orders by orders id.
	 *
	 * @param orderId the order id
	 * @param order   the order
	 * @return the orders
	 * @throws OrderNotFoundException the order not found exception
	 */
	public Orders updateOrdersByOrdersId(Long orderId, Orders order) throws OrderNotFoundException {
		Orders order2 = getOrderById(orderId);
		BeanUtils.copyProperties(order, order2);
		orderRepository.save(order2);
		return order2;
	}

}
