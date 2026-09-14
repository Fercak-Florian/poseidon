package com.nnk.springboot.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * This class is used to get a User by its UserName
 * This class uses the UserRepository interface to perform this action
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	 /**
     * This method is used to get a User in the database from its UserName
     * 
     * @param a String which is the UserName
     * @return a UserDetails object
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).get();
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			return new UserPrincipal(user);
		}
	}
}
