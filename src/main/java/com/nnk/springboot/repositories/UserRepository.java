package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * This interface contains necessary methods to perform CRUD actions to the
 * User table in database This interface inherits the JpaRepository
 * interface
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
		
	/**
	 * This method is a DerivedQuery which is used to find a User by its UserName
	 * 
	 * @param a String which is the UserName
	 * @return a Optional User
	 */
	public Optional<User> findByUsername(String username);
}
