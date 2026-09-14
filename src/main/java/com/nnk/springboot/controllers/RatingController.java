package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
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
 * This class contains necessary method to display a Rating list, and methods to
 * add, update and delete a Rating
 * This class uses the RatingService class to perform this actions
 */
@Slf4j
@Controller
public class RatingController {

	private RatingService ratingService;

	public RatingController(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	/**
     * This method gets all Ratings and displays a Rating list
     * 
     * @param a Model object 
     * @return a String which is the path to the HTML page
     */
	@RequestMapping("/rating/list")
	public String home(Model model) {
		List<Rating> ratings = ratingService.getRatings();
		model.addAttribute("ratings", ratings);
		log.info("display rating list");
		return "rating/list";
	}

	/**
     * This method displays the form to add a Rating
     * 
     * @param a Rating object 
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		log.info("display form to add rating");
		return "rating/add";
	}

	/**
     * This method validates a Rating and saves it if it contains no errors 
     * 
     * @param a Rating object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			ratingService.saveRating(rating);
			log.info("successful rating adding");
			return "redirect:/rating/list";
		}
		log.warn("rating not saved");
		return "rating/add";
	}

	/**
     * This method displays the form to update a Rating
     * 
     * @param a Rating id, a Model object
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingService.getRatingById(id);
		model.addAttribute("rating", rating);
		log.info("display form to update rating");
		return "rating/update";
	}

	/**
     * This method validates a Rating and updates it if it contains no errors 
     * 
     * @param a Rating id, a Rating object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			ratingService.updateRating(id, rating);
			log.info("successful rating updating");
			return "redirect:/rating/list";
		}
		log.warn("rating not updated");
		return "rating/update";
	}

	/**
     * This method deletes a Rating
     * 
     * @param a Rating id, a Model object
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		ratingService.deleteRating(id);
		log.info("successful rating deleting");
		return "redirect:/rating/list";
	}
}
