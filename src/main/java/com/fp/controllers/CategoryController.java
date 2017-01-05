package com.fp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fp.domain.Category;
import com.fp.domain.Outcome;
import com.fp.service.CategService;
import com.fp.service.OutcomeService;

@Controller
public class CategoryController {

	private final CategService categService;
	private final OutcomeService outcomeService;

	@Inject
	public CategoryController(final CategService categService,
			final OutcomeService outcomeService) {
		this.categService = categService;
		this.outcomeService = outcomeService;
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String list(Model model) {
		List<Category> list = categService.getList();
		model.addAttribute("categories", list);
		List<String> categsName = new ArrayList<String>();
		for (Category c : list) {
			categsName.add(c.getName());
		}
		List<String> outs = new ArrayList<String>();
		for (Outcome o : outcomeService.getList()) {
			outs.add(o.getCategory().getName());
		}
		categsName.removeAll(outs);
		model.addAttribute("categsNames", categsName);
		return "categories";
	}

	@RequestMapping("category/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("category", categService.getCtegById(id));
		return "categs_details";
	}

	@RequestMapping("category/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("category", categService.getCtegById(id));
		return "categs_form";
	}

	@RequestMapping("category/new")
	public String newProduct(Model model) {
		Category i = new Category();
		model.addAttribute("category", i);
		return "categs_form";
	}

	@RequestMapping(value = "category", method = RequestMethod.POST)
	public String save(Category product) {
		categService.save(product);
		return "redirect:/categories";
	}

	@RequestMapping("category/delete/{id}")
	public String delete(@PathVariable Long id) throws Exception {
		categService.deleteCateg(id);
		return "redirect:/categories";
	}

}
