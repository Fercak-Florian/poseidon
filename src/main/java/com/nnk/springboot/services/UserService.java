package com.nnk.springboot.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * This class contains necessary methods to get, add, update and delete a User
 * This class uses the UserRepository interface to perform this actions
 */
@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
     * This method finds all Users
     * 
     * @return a list of User
     */
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	/**
     * This method finds a User by its id in database
     * 
     * @param a User id
     * @return a User if it's found
     */
	public User getUserById(int id) {
		return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	}

	/**
     * This method saves a User
     * 
     * @param a User to save
     * @return the saved User
     */
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	/**
     * This method finds and updates a User
     * 
     * @param a User to update and its id in database
     * @return the updated User
     */
	public User updateUser(int id, User user) {
		User userToUpdate = userRepository.findById(id).get();
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setFullname(user.getFullname());
		userToUpdate.setRole(user.getRole());
		User updatedUser = userRepository.save(userToUpdate);
		return updatedUser;
	}
	
	/**
     * This method finds a User and deletes it
     * 
     * @param a User id in database
     */
	public void deleteUser(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id : " + id));
        userRepository.delete(user);
	}
}
