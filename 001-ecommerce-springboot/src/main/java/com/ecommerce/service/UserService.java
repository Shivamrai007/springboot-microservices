package com.ecommerce.service;

import com.ecommerce.dto.LoginRequestDTO;
import com.ecommerce.dto.Message;
import com.ecommerce.exceptions.UserNotFoundException;
import com.example.config.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 *
 * @author shivam.rai
 */
public interface UserService {

	/**
	 * Login.
	 *
	 * @param loginRequestDTO the login request DTO
	 * @return the user
	 * @throws UserNotFoundException the user not found exception
	 */
	public User login(LoginRequestDTO loginRequestDTO) throws UserNotFoundException;
	
	/**
	 * Gets the user by user id.
	 *
	 * @param userId the user id
	 * @return the user by user id
	 * @throws UserNotFoundException the user not found exception
	 */
	public User getUserByUserId(Long userId) throws UserNotFoundException;
}
