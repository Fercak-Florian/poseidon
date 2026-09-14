package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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
 * This class contains necessary method to display a CurvePoint list, and methods to
 * add, update and delete a CurvePoint
 * This class uses the CurvePointService class to perform this actions
 */
@Slf4j
@Controller
public class CurvePointController {

	private CurvePointService curvePointService;

	public CurvePointController(CurvePointService curvePointService) {
		this.curvePointService = curvePointService;
	}

	/**
     * This method gets all CurvePoints and displays a CurvePoint list
     * 
     * @param a Model object 
     * @return a String which is the path to the HTML page
     */
	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		List<CurvePoint> curvePoints = curvePointService.getCurvePoints();
		model.addAttribute("curvePoints", curvePoints);
		log.info("display CurvePoint list");
		return "curvePoint/list";
	}

	/**
     * This method displays the form to add a CurvePoint
     * 
     * @param a CurvePoint object 
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/curvePoint/add")
	public String addCurvePointForm(CurvePoint curvePoint) {
		log.info("display form to add CurvePoint");
		return "curvePoint/add";
	}

	/**
     * This method validates a CurvePoint and saves it if it contains no errors 
     * 
     * @param a CurvePoint object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			curvePointService.saveCurvePoint(curvePoint);
			log.info("successful CurvePoint adding");
			return "redirect:/curvePoint/list";
		}
		log.warn("curvePoint not saved");
		return "curvePoint/add";
	}

	/**
     * This method displays the form to update a CurvePoint
     * 
     * @param a CurvePoint id, a Model object
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curvePointService.getCurvePointById(id);
		model.addAttribute("curvePoint", curvePoint);
		log.info("display form to update CurvePoint");
		return "curvePoint/update";
	}

	/**
     * This method validates a CurvePoint and updates it if it contains no errors 
     * 
     * @param a CurvePoint id, CurvePoint object, a BindingResult object, a Model object
     * @return a String which is the path to the HTML page
     */
	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			curvePointService.updateCurvePoint(id, curvePoint);
			log.info("Successful CurvePoint updating");
			return "redirect:/curvePoint/list";
		}
		log.warn("curvePoint not updated");
		return "curvePoint/update";
	}

	/**
     * This method deletes a CurvePoint
     * 
     * @param a CurvePoint id, a Model object
     * @return a String which is the path to the HTML page
     */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		curvePointService.deleteCurvePoint(id);
		log.info("successful CurvePoint deleting");
		return "redirect:/curvePoint/list";
	}
}
