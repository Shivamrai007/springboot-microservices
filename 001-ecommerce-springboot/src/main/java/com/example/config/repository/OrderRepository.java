package com.example.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.config.model.Orders;

// TODO: Auto-generated Javadoc
/**
 * The Interface OrderRepository.
 *
 * @author shivam.rai
 */
public interface OrderRepository extends JpaRepository<Orders, Long>{

	/**
	 * Find order by user id.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<Orders> findOrderByUserId(String userId);
	

}
