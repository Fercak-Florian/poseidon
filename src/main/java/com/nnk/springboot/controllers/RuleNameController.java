package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

/**
 * This class contains necessary method to display a RuleName list, and methods to
 * add, update and delete a RuleName
 * This class uses the RuleNameService class to perform this actions
 */
@Slf4j
@Controller
public class RuleNameController {

	private RuleNameService ruleNameService;

	public RuleNameController(RuleNameService ruleNameService) {
		this.ruleNameService = ruleNameService;
	}

	/**
     * This method gets all RuleNames and displays a RuleName list
     * 
     * @param a Model object 
     * @return a String which is the path to the HTML page
     */
	@RequestMapping("/ruleName/list")
	public String home(Model model) {
		List<RuleName> ruleNames = ruleNameService.getRuleNames();
		model.addAttribute("ruleNames", ruleNames);
		log.info("display ruleName list");
		return "ruleName/list";
	}

	/**
     * This method displays the form to add a RuleName
     * 
     * @param a RuleName object 
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName ruleName) {
		log.info("display form to add ruleName");
		return "ruleName/add";
	}

	/**
     * This method validates a RuleName and saves it if it contains no errors 
     * 
     * @param a RuleName object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			ruleNameService.saveRuleName(ruleName);
			log.info("successful ruleName adding");
			return "redirect:/ruleName/list";
		}
		log.warn("ruleName not saved");
		return "ruleName/add";
	}

	/**
     * This method displays the form to update a RuleName
     * 
     * @param a RuleName id, a Model object
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		RuleName ruleName = ruleNameService.getRuleNameById(id);
		model.addAttribute("ruleName", ruleName);
		log.info("display form to update ruleName");
		return "ruleName/update";
	}

	/**
     * This method validates a RuleName and updates it if it contains no errors 
     * 
     * @param a RuleName id, a RuleName object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			ruleNameService.updateRuleName(id, ruleName);
			log.info("successful ruleName updating");
			return "redirect:/ruleName/list";
		}
		log.warn("ruleName not updated");
		return "ruleName/update";
	}
	
	/**
     * This method deletes a RuleName
     * 
     * @param a RuleName id, a Model object
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		ruleNameService.deleteRuleName(id);
		log.info("successful ruleName deleting");
		return "redirect:/ruleName/list";
	}
}
