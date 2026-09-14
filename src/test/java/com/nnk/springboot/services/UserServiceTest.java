package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	public void init() {
		userService = new UserService(userRepository);
	}
	
	@Test
	public void testGetUsers() {
		/*ARRANGE*/
		List<User> users = new ArrayList<>();
		users.add(new User("jboyd@email.com", "jboyd", "John Boyd", "USER"));
		when(userRepository.findAll()).thenReturn(users);
		/*ACT*/
		List<User> result = userService.getUsers();
		/*ASSERT*/
		assertThat(result.get(0).getUsername()).isEqualTo("jboyd@email.com");
		verify(userRepository).findAll();
	}

	@Test
	public void testGetUserById() {
		/*ARRANGE*/
		int id = 0;
		User user = new User("jboyd@email.com", "jboyd", "John Boyd", "USER");
		Optional<User> optUser = Optional.of(user);
		when(userRepository.findById(id)).thenReturn(optUser);
		/*ACT*/
		User result = userService.getUserById(id);
		/*ASSERT*/
		assertThat(result.getUsername()).isEqualTo("jboyd@email.com");
		verify(userRepository).findById(id);
	}
	
	@Test
	public void testGetUserByIdThrowsException() {
		/*ARRANGE*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> userService.getUserById(id));
	}
	
	@Test
	public void testSaveUser() {
		/*ARRANGE*/
		User user = new User("jboyd@email.com", "jboyd", "John Boyd", "USER");
		when(userRepository.save(user)).thenReturn(user);
		/*ACT*/
		User result = userService.saveUser(user);
		/*ASSERT*/
		assertThat(result.getUsername()).isEqualTo("jboyd@email.com");
		verify(userRepository).save(user);
	}
	
	@Test
	public void testUpdateUser() {
		/*ARRANGE*/
		int id = 0;
		User user = new User("jboyd@email.com", "jboyd", "John Boyd", "USER");
		Optional<User> optUser = Optional.of(user);
		when(userRepository.findById(id)).thenReturn(optUser);
		when(userRepository.save(user)).thenReturn(user);
		/*ACT*/
		User result = userService.updateUser(id, user);
		/*ASSERT*/
		assertThat(result.getUsername()).isEqualTo("jboyd@email.com");
		verify(userRepository).findById(id);
		verify(userRepository).save(user);
	}
	
	@Test
	public void testDeleteUser() {
		/*ARRANGE*/
		int id = 0;
		User user = new User("jboyd@email.com", "jboyd", "John Boyd", "USER");
		Optional<User> optUser = Optional.of(user);
		when(userRepository.findById(id)).thenReturn(optUser);
		/*ACT*/
		userService.deleteUser(id);
		/*ASSERT*/
		verify(userRepository).findById(id);
	}
	
	@Test
	public void testDeleteUserThrowsException() {
		/*ARRANGE*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(id));
	}
}
