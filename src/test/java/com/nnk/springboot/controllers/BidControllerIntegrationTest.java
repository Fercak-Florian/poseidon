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
import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BidControllerIntegrationTest {

	@Autowired
	public MockMvc mockMvc;
	
	@Autowired
	public BidRepository bidRepository;
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testHome() throws Exception {
		mockMvc.perform(get("/bid/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("bid/list"))
		.andExpect(content().string(containsString("Home")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testAddBidForm() throws Exception {
		mockMvc.perform(get("/bid/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("bid/add"))
		.andExpect(content().string(containsString("Add New Bid")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testValidateBid() throws Exception{
		mockMvc.perform(post("/bid/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("account", "testAccount")
		.param("type", "testType")
		.param("bidQuantity", "1.0")
		.with(csrf()))
		.andExpect(redirectedUrl("/bid/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testValidateBidWhenResultHasError() throws Exception{
		mockMvc.perform(post("/bid/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("account", "")
		.param("type", "")
		.param("bidQuantity", "")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("bid/add"))
		.andExpect(content().string(containsString("Add New Bid")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testShowUpdateBidForm() throws Exception {
		Bid bid = bidRepository.save(new Bid("account", "type", 1.0));
		mockMvc.perform(get("/bid/update/{id}", bid.getId()))
		.andExpect(status().isOk())
		.andExpect(view().name("bid/update"))
		.andExpect(content().string(containsString("Update Bid")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testUpdateBid() throws Exception{
		Bid bid = bidRepository.save(new Bid("account", "type", 1.0));
		mockMvc.perform(post("/bid/update/{id}", bid.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("account", "modifiedAccount")
		.param("type", "modifiedType")
		.param("bidQuantity", "2.0")
		.with(csrf()))
		.andExpect(redirectedUrl("/bid/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testUpdateBidWhenResultHasError() throws Exception{
		Bid bid = bidRepository.save(new Bid("account", "type", 1.0));
		mockMvc.perform(post("/bid/update/{id}", bid.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("account", "")
		.param("type", "")
		.param("bidQuantity", "")
		.with(csrf()))
		.andExpect(view().name("bid/update"))
		.andExpect(content().string(containsString("Update Bid")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testDeleteBid() throws Exception {
		Bid bid = bidRepository.save(new Bid("account", "type", 1.0));
		mockMvc.perform(get("/bid/delete/{id}", bid.getId()))
		.andExpect(redirectedUrl("/bid/list"))
		.andExpect(status().isFound());
	}
}
