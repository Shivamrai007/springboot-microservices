package com.ecommerce.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.LoginRequestDTO;
import com.ecommerce.exceptions.UserNotFoundException;
import com.ecommerce.service.UserService;
import com.example.config.model.User;
import com.example.config.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 *
 * @author shivam.rai
 */
@Service
public class UserServiceImpl implements UserService{
	
	/** The user repository. */
	@Autowired
	UserRepository userRepository;
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	/**
	 * Login.
	 *
	 * @param loginRequestDTO the login request DTO
	 * @return the user
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public User login(LoginRequestDTO loginRequestDTO) throws UserNotFoundException {
		
		User user =userRepository.getUserByUsernameAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
		
		if (user != null) {
			logger.info("User found with username : {}",loginRequestDTO.getUsername());
			return user;
		}else {
			logger.error("User not found with username : {}",loginRequestDTO.getUsername());
			throw new UserNotFoundException("User Not Found");
			
		}
		
	}
	
	/**
	 * Gets the user by user id.
	 *
	 * @param userId the user id
	 * @return the user by user id
	 * @throws UserNotFoundException the user not found exception
	 */
	public User getUserByUserId(Long userId) throws UserNotFoundException {
		User user = userRepository.getUserByUserId(userId);
		
		if (user != null) {
			logger.info("User found with userId : {}",userId);
			return user;
		}else{
			logger.error("User not found with userId : {}",userId);
			throw new UserNotFoundException("User Not Found with userId : "+userId);
		}
	}

	
}
