package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.utils.FormComment;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class contains necessary method to enable the login of a User
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    
    FormComment errorLoginFormComment = new FormComment();

    /**
     * This method enables a user to log to the application
     * 
     * @param a Model object, a Boolean RequestParam
     * @return a String which is the path to the HTML page
     */
    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", defaultValue = "false") boolean error) {
    	errorLoginFormComment.setError(error);
		errorLoginFormComment.setMessage("Bad credentials");
		model.addAttribute("formComment", errorLoginFormComment);
		log.info("Display login page");
    	return "login";
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
