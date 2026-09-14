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
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CurvePointControllerIntergrationTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Autowired
	public CurvePointRepository curvePointRepository;
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testHome() throws Exception {
		mockMvc.perform(get("/curvePoint/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/list"))
		.andExpect(content().string(containsString("Home")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testAddCurvePointForm() throws Exception {
		mockMvc.perform(get("/curvePoint/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/add"))
		.andExpect(content().string(containsString("Add New Curve Point")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testValidateCurvePoint() throws Exception{
		mockMvc.perform(post("/curvePoint/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("curveId", "1")
		.param("term", "2.0")
		.param("value", "1.0")
		.with(csrf()))
		.andExpect(redirectedUrl("/curvePoint/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testValidateCurvePointWhenResultHasError() throws Exception{
		mockMvc.perform(post("/curvePoint/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("curveId", "")
		.param("term", "")
		.param("value", "")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/add"))
		.andExpect(content().string(containsString("Add New Curve Point")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testShowUpdateCurvePointForm() throws Exception {
		CurvePoint curvePoint = curvePointRepository.save(new CurvePoint(1, 1.0, 1.0));
		mockMvc.perform(get("/curvePoint/update/{id}", curvePoint.getId()))
		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/update"))
		.andExpect(content().string(containsString("Update CurvePoint")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testUpdateCurvePoint() throws Exception{
		CurvePoint curvePoint = curvePointRepository.save(new CurvePoint(1, 1.0, 1.0));
		mockMvc.perform(post("/curvePoint/update/{id}", curvePoint.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("curveId", "2")
		.param("term", "3.0")
		.param("value", "4.0")
		.with(csrf()))
		.andExpect(redirectedUrl("/curvePoint/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testUpdateCurvePointWhenResultHasError() throws Exception{
		CurvePoint curvePoint = curvePointRepository.save(new CurvePoint(1, 1.0, 1.0));
		mockMvc.perform(post("/curvePoint/update/{id}", curvePoint.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("curveId", "")
		.param("term", "")
		.param("value", "")
		.with(csrf()))
		.andExpect(view().name("curvePoint/update"))
		.andExpect(content().string(containsString("Update CurvePoint")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testDeleteCurvePoint() throws Exception {
		CurvePoint curvePoint = curvePointRepository.save(new CurvePoint(1, 1.0, 1.0));
		mockMvc.perform(get("/curvePoint/delete/{id}", curvePoint.getId()))
		.andExpect(redirectedUrl("/curvePoint/list"))
		.andExpect(status().isFound());
	}
}
