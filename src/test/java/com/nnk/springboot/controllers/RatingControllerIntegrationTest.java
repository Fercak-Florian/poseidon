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
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerIntegrationTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Autowired
	public RatingRepository ratingRepository; 
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testHome() throws Exception {
		mockMvc.perform(get("/rating/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("rating/list"))
		.andExpect(content().string(containsString("Home")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testAddRatingForm() throws Exception {
		mockMvc.perform(get("/rating/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("rating/add"))
		.andExpect(content().string(containsString("Add New Rating")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testValidateRating() throws Exception{
		mockMvc.perform(post("/rating/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("moodysRating", "testMoodysRating")
		.param("sandRating", "testSandRating")
		.param("fitchRating", "testFitchRating")
		.param("orderNumber", "1")
		.with(csrf()))
		.andExpect(redirectedUrl("/rating/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testValidateRatingWhenResultHasError() throws Exception{
		mockMvc.perform(post("/rating/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("moodysRating", "")
		.param("sandRating", "")
		.param("fitchRating", "")
		.param("orderNumber", "")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("rating/add"))
		.andExpect(content().string(containsString("Add New Rating")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testShowUpdateRatingForm() throws Exception {
		Rating rating = ratingRepository.save(new Rating("MoodysRating1", "SandRating1", "FitchRating1", 1));
		mockMvc.perform(get("/rating/update/{id}", rating.getId()))
		.andExpect(status().isOk())
		.andExpect(view().name("rating/update"))
		.andExpect(content().string(containsString("Update Rating")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testUpdateRating() throws Exception{
		Rating rating = ratingRepository.save(new Rating("MoodysRating1", "SandRating1", "FitchRating1", 1));
		mockMvc.perform(post("/rating/update/{id}", rating.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("moodysRating", "modifiedMoodysRating")
		.param("sandRating", "modifiedSandRating")
		.param("fitchRating", "modifiedFitchRating")
		.param("orderNumber", "2")
		.with(csrf()))
		.andExpect(redirectedUrl("/rating/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testUpateRatingWhenResultHasError() throws Exception{
		Rating rating = ratingRepository.save(new Rating("MoodysRating1", "SandRating1", "FitchRating1", 1));
		mockMvc.perform(post("/rating/update/{id}", rating.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("curveId", "")
		.param("term", "")
		.param("value", "")
		.with(csrf()))
		.andExpect(view().name("rating/update"))
		.andExpect(content().string(containsString("Update Rating")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testDeleteRating() throws Exception {
		Rating rating = ratingRepository.save(new Rating("MoodysRating1", "SandRating1", "FitchRating1", 1));
		mockMvc.perform(get("/rating/delete/{id}" ,rating.getId()))
		.andExpect(redirectedUrl("/rating/list"))
		.andExpect(status().isFound());
	}
}
