package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

/**
 * This class contains necessary method to display a User list, and methods to
 * add, update and delete a User
 * This class uses the UserService class to perform this actions
 */
@Slf4j
@Controller
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
     * This method gets all Users and displays a User list
     * 
     * @param a Model object 
     * @return a String which is the path to the HTML page
     */
	@RequestMapping("/user/list")
	public String home(Model model) {
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		log.info("display user list");
		return "user/list";
	}

	/**
     * This method displays the form to add a User
     * 
     * @param a User object 
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/user/add")
	public String addUser(User user) {
		log.info("display form to add user");
		return "user/add";
	}

	/**
     * This method validates a User and saves it if it contains no errors 
     * 
     * @param a User object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			userService.saveUser(user);
			log.info("successful user adding");
			return "redirect:/user/list";
		}
		log.warn("user not saved");
		return "user/add";
	}

	/**
     * This method displays the form to update a User
     * 
     * @param a User id, a Model object
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		User user = userService.getUserById(id);
		user.setPassword("");
		model.addAttribute("user", user);
		log.info("display form to update user");
		return "user/update";
	}

	/**
     * This method validates a User and updates it if it contains no errors 
     * 
     * @param a User id, a User object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			log.warn("user not updated");
			return "user/update";
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setId(id);
		userService.saveUser(user);
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		log.info("successful user updating");
		return "redirect:/user/list";
	}

	/**
     * This method deletes a User
     * 
     * @param a User id, a Model object
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		userService.deleteUser(id);
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		log.info("successful user deleting");
		return "redirect:/user/list";
	}
}
