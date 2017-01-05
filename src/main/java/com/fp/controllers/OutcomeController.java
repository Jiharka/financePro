package com.fp.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fp.domain.Outcome;
import com.fp.service.CategService;
import com.fp.service.OutcomeService;

@Controller
public class OutcomeController {

	private final OutcomeService outcomeService;
	private final CategService categService;

	@Inject
	public OutcomeController(final OutcomeService service,
			final CategService categService) {
		this.outcomeService = service;
		this.categService = categService;
	}
	
	@RequestMapping(value = "/outcomes", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("outcomes", outcomeService.getList());
		model.addAttribute("plannedSum", outcomeService.getPlannedSum());
		model.addAttribute("factSum", outcomeService.getFactSum());
		return "outcomes";
	}

	@RequestMapping("outcome/{id}")
	public String showProduct(@PathVariable Long id, Model model) {
		model.addAttribute("outcome", outcomeService.getOutcomeById(id));
		return "outcomeshow";
	}

	@RequestMapping("outcome/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("outcome", outcomeService.getOutcomeById(id));
		model.addAttribute("allCateg",categService.getList());
		return "outcomeform";
	}

	@RequestMapping("outcome/new")
	public String newProduct(Model model) {
		Outcome outcome = new Outcome();
		model.addAttribute("allCateg",categService.getList());
		model.addAttribute("outcome", outcome);
		return "outcomeform";
	}

	@RequestMapping(value = "outcome", method = RequestMethod.POST)
	public String saveProduct(Outcome product) {
		outcomeService.save(product);
		return "redirect:/outcomes";
	}

	@RequestMapping("outcome/delete/{id}")
	public String delete(@PathVariable Long id) {
		outcomeService.deleteOutcome(id);
		return "redirect:/outcomes";
	}
	
}
