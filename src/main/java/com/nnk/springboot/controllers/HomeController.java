package com.nnk.springboot.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

/**
 * This class contains necessary method to display the home page
 */
@Slf4j
@Controller
public class HomeController {

	/**
     * This displays the home page
     * 
     * @param a Model object 
     * @return a String which is the path to the HTML page
     */
	@RequestMapping("/")
	public String home(Model model) {
		log.info("display home page");
		return "home";
	}

	/**
     * This displays the home page
     * 
     * @param a Model object 
     * @return a String which is the path to the HTML page
     */
	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		return "redirect:/bid/list";
	}
}
