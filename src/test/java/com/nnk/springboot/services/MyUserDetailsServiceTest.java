package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class MyUserDetailsServiceTest {
	
	private MyUserDetailsService userDetailsService;
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	public void init() {
		userDetailsService = new MyUserDetailsService(userRepository);
	}
	
	@Test
	public void testLoadUserByUserNameSucceed() {
		/* ARRANGE */
		User user = new User();
		user.setUsername("jboyd@email.com");
		user.setPassword("jboyd");
		user.setRole("USER");
		Optional<User> optUser = Optional.of(user);
		/* ACT */
		when(userRepository.findByUsername("jboyd@email.com")).thenReturn(optUser);
		UserDetails result = userDetailsService.loadUserByUsername("jboyd@email.com");
		/* ASSERT */
		assertThat(result.getUsername()).isEqualTo("jboyd@email.com");
		verify(userRepository).findByUsername("jboyd@email.com");
	}
}
