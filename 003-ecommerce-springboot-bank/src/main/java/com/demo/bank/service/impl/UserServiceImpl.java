package com.demo.bank.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bank.model.User;
import com.demo.bank.repository.UserRepository;
import com.demo.bank.service.UserService;


// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 *
 * @author shivam.rai
 */
@Service
public class UserServiceImpl implements UserService {

	/** The user repository. */
	@Autowired
	UserRepository userRepository;
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	/**
	 * Gets the user by user id.
	 *
	 * @param userId the user id
	 * @return the user by user id
	 */
	public User getUserByUserId(Long userId) {
		logger.info("Fetch user by user id : {}",userId);
		return userRepository.getUserById(userId);
		
	}
	
}