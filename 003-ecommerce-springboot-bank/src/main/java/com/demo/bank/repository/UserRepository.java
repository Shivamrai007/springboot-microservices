package com.demo.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.bank.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 *
 * @author shivam.rai
 */
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 */
	public User getUserById(Long id);

}
