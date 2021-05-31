package com.example.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.config.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 *
 * @author shivam.rai
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	/**
	 * Gets the user by username and password.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the user by username and password
	 */
	public User getUserByUsernameAndPassword(String username,String password);
	
	/**
	 * Gets the user by user id.
	 *
	 * @param userId the user id
	 * @return the user by user id
	 */
	public User getUserByUserId(Long userId);

}
