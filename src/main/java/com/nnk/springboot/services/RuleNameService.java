package com.nnk.springboot.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

/**
 * This class contains necessary methods to get, add, update and delete a RuleName
 * This class uses the RuleNameRepository interface to perform this actions
 */
@Service
public class RuleNameService {
	
	private RuleNameRepository ruleNameRepository;
	
	public RuleNameService(RuleNameRepository ruleNameRepository) {
		this.ruleNameRepository = ruleNameRepository;
	}

	/**
     * This method finds all RuleName
     * 
     * @return a list of RuleName
     */
	public List<RuleName> getRuleNames(){
		return ruleNameRepository.findAll();
	}
	
	/**
     * This method finds a RuleName by its id in database
     * 
     * @param a RuleName id
     * @return a RuleName if it's found
     */
	public RuleName getRuleNameById(int id){
		return ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id : " + id));
	}
	
	/**
     * This method saves a RuleName
     * 
     * @param a RuleName to save
     * @return the saved RuleName
     */
	public RuleName saveRuleName(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}
	
	/**
     * This method finds and updates a RuleName
     * 
     * @param a RuleName to update and its id in database
     * @return the updated RuleName
     */
	public RuleName updateRuleName(int id, RuleName ruleName) {
		RuleName ruleNameToUpdate = ruleNameRepository.findById(id).get();
		ruleNameToUpdate.setName(ruleName.getName());
		ruleNameToUpdate.setDescription(ruleName.getDescription());
		ruleNameToUpdate.setJson(ruleName.getJson());
		ruleNameToUpdate.setTemplate(ruleName.getTemplate());
		ruleNameToUpdate.setSqlStr(ruleName.getSqlStr());
		ruleNameToUpdate.setSqlPart(ruleName.getSqlPart());
		return ruleNameRepository.save(ruleNameToUpdate);
	}
	
	/**
     * This method finds a RuleName and deletes it
     * 
     * @param a RuleName id in database
     */
	public void deleteRuleName(int id) {
		RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id : " + id));
		ruleNameRepository.delete(ruleName);
	}
}
