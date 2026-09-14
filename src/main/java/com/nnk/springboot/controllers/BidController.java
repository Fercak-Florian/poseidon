package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.services.BidService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class contains necessary method to display a bid list, and methods to
 * add, update and delete a Bid
 * This class uses the BidService class to perform this actions
 */
@Slf4j
@Controller
public class BidController {

	private BidService bidService;

	public BidController(BidService bidListService) {
		this.bidService = bidListService;
	}

	/**
     * This method gets all Bids and displays a Bid list
     * 
     * @param a Model object 
     * @return a String which is the path to the HTML page
     */
	@RequestMapping("/bid/list")
	public String home(Model model) {
		List<Bid> bids = bidService.getBids();
		model.addAttribute("bids", bids);
		log.info("display bid list");
		return "bid/list";
	}

	/**
     * This method displays the form to add a Bid
     * 
     * @param a Bid object 
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/bid/add")
	public String addBidForm(Bid bid) {
		log.info("display form to add bid");
		return "bid/add";
	}

	/**
     * This method validates a Bid and saves it if it contains no errors 
     * 
     * @param a Bid object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
	@PostMapping("/bid/validate")
	public String validate(@Valid Bid bid, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			bidService.saveBid(bid);
			log.info("successful bid adding");
			return "redirect:/bid/list";
		}
		log.warn("bid not saved");
		return "bid/add";
	}

	/**
     * This method displays the form to update a Bid
     * 
     * @param a Bid id, a Model object
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/bid/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Bid bid = bidService.getBidById(id);
		model.addAttribute("bid", bid);
		log.info("display form to update bid");
		return "bid/update";
	}

	/**
     * This method validates a Bid and updates it if it contains no errors 
     * 
     * @param a Bid id, Bid object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
	@PostMapping("/bid/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid Bid bid, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			bidService.updateBid(id, bid);
			log.info("successful bid updating");
			return "redirect:/bid/list";
		}
		log.warn("bid not updated");
		return "bid/update";
	}

	/**
     * This method deletes a Bid
     * 
     * @param Bid id, a Model object
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/bid/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		bidService.deleteBid(id);
		log.info("successful bid deleting");
		return "redirect:/bid/list";
	}
}
