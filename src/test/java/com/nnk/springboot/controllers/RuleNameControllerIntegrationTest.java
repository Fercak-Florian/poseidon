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
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class RuleNameControllerIntegrationTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Autowired
	public RuleNameRepository ruleNameRepository;
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testHome() throws Exception {
		mockMvc.perform(get("/ruleName/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/list"))
		.andExpect(content().string(containsString("Home")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testAddRuleNameForm() throws Exception {
		mockMvc.perform(get("/ruleName/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/add"))
		.andExpect(content().string(containsString("Add New Rule")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testValidateRuleName() throws Exception{
		mockMvc.perform(post("/ruleName/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("name", "testName")
		.param("description", "testDescription")
		.param("json", "testJson")
		.param("template", "testTemplate")
		.param("sqlStr", "testSqlStr")
		.param("sqlPart", "testSqlPart")
		.with(csrf()))
		.andExpect(redirectedUrl("/ruleName/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testValidateRuleNameWhenResultHasError() throws Exception{
		mockMvc.perform(post("/ruleName/validate")
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("name", "")
		.param("description", "")
		.param("json", "")
		.param("template", "")
		.param("sqlStr", "")
		.param("sqlPart", "")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/add"))
		.andExpect(content().string(containsString("Add New Rule")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testShowUpdateRuleNameForm() throws Exception {
		RuleName ruleName = ruleNameRepository.save(new RuleName("name", "description", "json", "template", "sqlStr", "sqlpart"));
		mockMvc.perform(get("/ruleName/update/{id}", ruleName.getId()))
		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/update"))
		.andExpect(content().string(containsString("Update Rule")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testUpdateRuleName() throws Exception{
		RuleName ruleName = ruleNameRepository.save(new RuleName("name", "description", "json", "template", "sqlStr", "sqlpart"));
		mockMvc.perform(post("/ruleName/update/{id}", ruleName.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("name", "modifiedName")
		.param("description", "modifiedDescription")
		.param("json", "modifiedJson")
		.param("template", "modifiedTemplate")
		.param("sqlStr", "modifiedSqlStr")
		.param("sqlPart", "modifiedSqlPart")
		.with(csrf()))
		.andExpect(redirectedUrl("/ruleName/list"))
		.andExpect(status().isFound());
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testUpateRuleNameWhenResultHasError() throws Exception{
		RuleName ruleName = ruleNameRepository.save(new RuleName("name", "description", "json", "template", "sqlStr", "sqlpart"));
		mockMvc.perform(post("/ruleName/update/{id}", ruleName.getId())
		.contentType(MediaType.parseMediaType("application/x-www-form-urlencoded"))
		.param("curveId", "")
		.param("term", "")
		.param("value", "")
		.with(csrf()))
		.andExpect(view().name("ruleName/update"))
		.andExpect(content().string(containsString("Update Rule")));
	}
	
	@Test
	@WithMockUser("jboyd@email.com")
	public void testDeleteRuleName() throws Exception {
		RuleName ruleName = ruleNameRepository.save(new RuleName("name", "description", "json", "template", "sqlStr", "sqlpart"));
		mockMvc.perform(get("/ruleName/delete/{id}", ruleName.getId()))
		.andExpect(redirectedUrl("/ruleName/list"))
		.andExpect(status().isFound());
	}
}
