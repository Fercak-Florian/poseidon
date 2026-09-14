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
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerIntegrationTest {

	@Autowired
	public MockMvc mockMvc;
	
	 @Autowired
	 public TradeRepository tradeRepository;
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testHome() throws Exception {
		mockMvc.perform(get("/trade/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("trade/list"))
		.andExpect(content().string(containsString("Home")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testAddTradeForm() throws Exception {
		mockMvc.perform(get("/trade/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("trade/add"))
		.andExpect(content().string(containsString("Add New Trade")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testValidateTrade() throws Exception{
		mockMvc.perform(post("/trade/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("account", "testAccount")
		.param("type", "testType")
		.param("buyQuantity", "2.0")
		.with(csrf()))
		.andExpect(redirectedUrl("/trade/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testValidateTradeWhenResultHasError() throws Exception{
		mockMvc.perform(post("/trade/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("account", "")
		.param("type", "")
		.param("buyQuantity", "")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("trade/add"))
		.andExpect(content().string(containsString("Add New Trade")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testShowUpdateTradeForm() throws Exception {
		Trade trade = tradeRepository.save(new Trade("account", "type", 1.0));
		mockMvc.perform(get("/trade/update/{id}", trade.getId()))
		.andExpect(status().isOk())
		.andExpect(view().name("trade/update"))
		.andExpect(content().string(containsString("Update Trade")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testUpdateTrade() throws Exception{
		Trade trade = tradeRepository.save(new Trade("account", "type", 1.0));
		mockMvc.perform(post("/trade/update/{id}", trade.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("account", "modifiedAccount")
		.param("type", "modifiedType")
		.param("buyQuantity", "3.0")
		.with(csrf()))
		.andExpect(redirectedUrl("/trade/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testUpateTradeWhenResultHasError() throws Exception{
		Trade trade = tradeRepository.save(new Trade("account", "type", 1.0));
		mockMvc.perform(post("/trade/update/{id}", trade.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("account", "")
		.param("type", "")
		.param("buyQuantity", "")
		.with(csrf()))
		.andExpect(view().name("trade/update"))
		.andExpect(content().string(containsString("Update Trade")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testDeleteTrade() throws Exception {
		Trade trade = tradeRepository.save(new Trade("account", "type", 1.0));
		mockMvc.perform(get("/trade/delete/{id}", trade.getId()))
		.andExpect(redirectedUrl("/trade/list"))
		.andExpect(status().isFound());
	}
}
