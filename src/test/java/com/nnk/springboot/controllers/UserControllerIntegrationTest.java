package com.nnk.springboot.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Autowired
	public UserRepository userRepository;
	
	@Test
	@WithMockUser(authorities = "ADMIN")
	public void testHome() throws Exception {
		mockMvc.perform(get("/user/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/list"))
		.andExpect(content().string(containsString("User List")));
	}
	
	@Test
	@WithMockUser(authorities = "ADMIN")
	public void testAddUserForm() throws Exception {
		mockMvc.perform(get("/user/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/add"))
		.andExpect(content().string(containsString("Add New User")));
	}
	
	@Test
	@WithMockUser(authorities = "ADMIN")
	public void testValidateUser() throws Exception{
		mockMvc.perform(post("/user/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("fullname", "testFullName")
		.param("username", "testUsername")
		.param("password", "Password@1")
		.param("role", "USER")
		.with(csrf()))
		.andExpect(redirectedUrl("/user/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser(authorities = "ADMIN")
	public void testValidateUserWhenResultHasError() throws Exception{
		mockMvc.perform(post("/user/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("fullname", "")
		.param("username", "")
		.param("password", "")
		.param("role", "")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("user/add"))
		.andExpect(content().string(containsString("Add New User")));
	}
	
	@Test
	@WithMockUser(authorities = "ADMIN")
	public void testShowUpdateUserForm() throws Exception {
		User user = userRepository.save(new User("username", "Password@1", "fullname", "USER"));
		mockMvc.perform(get("/user/update/{id}", user.getId()))
		.andExpect(status().isOk())
		.andExpect(view().name("user/update"))
		.andExpect(content().string(containsString("Update User")));
	}
	
	@Test
	@WithMockUser(authorities = "ADMIN")
	public void testUpdateUser() throws Exception{
		User user = userRepository.save(new User("username", "Password@1", "fullname", "USER"));
		mockMvc.perform(post("/user/update/{id}", user.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("fullname", "modifiedFullName")
		.param("username", "modifiedUsername")
		.param("password", "Password@2")
		.param("role", "ADMIN")
		.with(csrf()))
		.andExpect(redirectedUrl("/user/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser(authorities = "ADMIN")
	public void testUpateUserWhenResultHasError() throws Exception{
		User user = userRepository.save(new User("username", "Password@1", "fullname", "USER"));
		mockMvc.perform(post("/user/update/{id}", user.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("fullname", "")
		.param("username", "")
		.param("password", "")
		.param("role", "")
		.with(csrf()))
		.andExpect(view().name("user/update"))
		.andExpect(content().string(containsString("Update User")));
	}
	
	@Test
	@WithMockUser(authorities = "ADMIN")
	public void testDeleteUser() throws Exception {
		User user = userRepository.save(new User("username", "Password@1", "fullname", "USER"));
		mockMvc.perform(get("/user/delete/{id}", user.getId()))
		.andExpect(redirectedUrl("/user/list"))
		.andExpect(status().isFound());
	}
}
