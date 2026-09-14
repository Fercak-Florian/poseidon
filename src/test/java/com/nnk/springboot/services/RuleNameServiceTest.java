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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@ExtendWith(MockitoExtension.class)
public class RuleNameServiceTest {
	
	private RuleNameService ruleNameService;
	
	@Mock
	private RuleNameRepository ruleNameRepository;
	
	@BeforeEach
	public void init() {
		ruleNameService = new RuleNameService(ruleNameRepository);
	}
	
	@Test
	public void testGetRuleNames() {
		/*ARRANGE*/
		List<RuleName> ruleNames = new ArrayList<>();
		ruleNames.add(new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart"));
		when(ruleNameRepository.findAll()).thenReturn(ruleNames);
		/*ACT*/
		List<RuleName> result = ruleNameService.getRuleNames();
		/*ASSERT*/
		assertThat(result.get(0).getName()).isEqualTo("name");
		verify(ruleNameRepository).findAll();
	}
	
	@Test
	public void testGetRuleNameById() {
		/*ARRANGE*/
		int id = 0;
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		Optional<RuleName> optRuleName = Optional.of(ruleName);
		when(ruleNameRepository.findById(id)).thenReturn(optRuleName);
		/*ACT*/
		RuleName result = ruleNameService.getRuleNameById(id);
		/*ASSERT*/
		assertThat(result.getName()).isEqualTo("name");
		verify(ruleNameRepository).findById(id);
	}
	
	@Test
	public void testGetRuleNameByIdThrowsException() {
		/*ARRANGE*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> ruleNameService.getRuleNameById(id));
	}
	
	@Test
	public void testSaveRuleName() {
		/*ARRANGE*/
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
		/*ACT*/
		RuleName result = ruleNameService.saveRuleName(ruleName);
		/*ASSERT*/
		assertThat(result.getName()).isEqualTo("name");
		verify(ruleNameRepository).save(ruleName);
	}
	
	@Test
	public void testUpdateRuleName() {
		/*ARRANGE*/
		int id = 0;
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		Optional<RuleName> optRuleName = Optional.of(ruleName);
		when(ruleNameRepository.findById(id)).thenReturn(optRuleName);
		when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
		/*ACT*/
		RuleName result = ruleNameService.updateRuleName(id, ruleName);
		/*ASSERT*/
		assertThat(result.getName()).isEqualTo("name");
		verify(ruleNameRepository).findById(id);
		verify(ruleNameRepository).save(ruleName);
	}
	
	@Test
	public void testDeleteRuleName() {
		/*ARRANGE*/
		int id = 0;
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		Optional<RuleName> optRuleName = Optional.of(ruleName);
		when(ruleNameRepository.findById(id)).thenReturn(optRuleName);
		/*ACT*/
		ruleNameService.deleteRuleName(id);
		/*ASSERT*/
		verify(ruleNameRepository).findById(id);
	}
	
	@Test
	public void testDeleteRlenameThrowsException() {
		/*ARRANEG*/
		int id = 0;
		/*ACT*/
		/*ASSERT*/
		assertThrows(IllegalArgumentException.class, () -> ruleNameService.deleteRuleName(id));
	}
}
