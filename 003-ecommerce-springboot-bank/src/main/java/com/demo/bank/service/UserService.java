package com.demo.bank.service;

import com.demo.bank.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 *
 * @author shivam.rai
 */
public interface UserService {


	/**
	 * Gets the user by user id.
	 *
	 * @param userId the user id
	 * @return the user by user id
	 */
	public User getUserByUserId(Long userId);
}