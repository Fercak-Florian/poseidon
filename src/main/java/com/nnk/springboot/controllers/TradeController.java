package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

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
 * This class contains necessary method to display a Trade list, and methods to
 * add, update and delete a Trade
 * This class uses the TradeService class to perform this actions
 */
@Slf4j
@Controller
public class TradeController {
	
	private TradeService tradeService;
	
	public TradeController(TradeService tradeService) {
		this.tradeService = tradeService;
	}

	/**
     * This method gets all Trades and displays a Trade list
     * 
     * @param a Model object 
     * @return a String which is the path to the HTML page
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
    	List<Trade> trades = tradeService.getTrades();
    	model.addAttribute("trades", trades);
    	log.info("display trade list");
        return "trade/list";
    }

    /**
     * This method displays the form to add a Trade
     * 
     * @param a Trade object 
     * @return a String which is the path to the HTML page
     */
    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
    	log.info("display form to add trade");
        return "trade/add";
    }

    /**
     * This method validates a Trade and saves it if it contains no errors 
     * 
     * @param a Trade object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
    	if(!result.hasErrors()) {
    		tradeService.saveTrade(trade);
        	log.info("successful trade adding");
            return "redirect:/trade/list";
    	}
    	log.warn("trade not saved");
    	return "trade/add";
    }

    /**
     * This method displays the form to update a Trade
     * 
     * @param a Trade id, a Model object
     * @return a String which is the path to the HTML page
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Trade trade = tradeService.getTradeById(id);
    	model.addAttribute("trade", trade);
    	log.info("display form to update trade");
        return "trade/update";
    }

    /**
     * This method validates a Trade and updates it if it contains no errors 
     * 
     * @param a Trade id, a Trade object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	if(!result.hasErrors()) {
    		tradeService.updateTrade(id, trade);
        	log.info("successful trade updating");
        	return "redirect:/trade/list";
    	}
    	log.warn("trade not updated");
    	return "trade/update";
    }

    /**
     * This method deletes a Trade
     * 
     * @param a Trade id, a Model object
     * @return a String which is the path to the HTML page
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	tradeService.deleteTrade(id);
    	log.info("successful trade deleting");
        return "redirect:/trade/list";
    }
}
