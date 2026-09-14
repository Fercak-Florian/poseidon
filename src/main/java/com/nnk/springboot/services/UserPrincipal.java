package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.nnk.springboot.domain.User;

/**
 * This class is used to transform a User to a UserDetails object and implements
 * the UserDetails interface
 */
public class UserPrincipal implements UserDetails {

	private String username;
	private String password;
	private List<GrantedAuthority> roles;

	/**
	 * This constructor is used to transform a user object to a UserDetails object
	 * 
	 * @param a User object
	 */
	public UserPrincipal(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(user.getRole()));
	}

	/**
	 * This method is used to get User authorities
	 * 
	 * @return a collection of GrantedAuthority
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	/**
	 * This method is used to get User password
	 * 
	 * @return a String
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * This method is used to get User UserName
	 * 
	 * @return a String
	 */
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}