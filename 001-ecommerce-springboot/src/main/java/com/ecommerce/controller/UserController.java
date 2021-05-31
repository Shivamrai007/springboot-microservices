package com.ecommerce.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.LoginRequestDTO;
import com.ecommerce.dto.Message;
import com.ecommerce.exceptions.UserNotFoundException;
import com.ecommerce.service.UserService;
import com.example.config.model.User;


// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
@RequestMapping("users")
@CrossOrigin //enabled for swagger for running on api gateway port.
public class UserController {
	
	/** The user service. */
	@Autowired
	UserService userService;
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * Login.
	 *
	 * @param loginRequestDTO the login request DTO
	 * @return the response entity
	 * @throws UserNotFoundException the user not found exception
	 */
	@PostMapping
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws UserNotFoundException {
		
		User user = userService.login(loginRequestDTO); 
		
		if (user != null) {
			logger.info("User is successfully logged in with username : {}",user.getUsername());
			return ResponseEntity.ok(new Message("User is successfully logged in !!!"));
		}else{
			logger.error("User login failed with username : {}",loginRequestDTO.getUsername());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Wrong Credentials"));
		}
		
	}
	
}
