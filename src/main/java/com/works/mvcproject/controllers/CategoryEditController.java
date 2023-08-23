package com.works.mvcproject.controllers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.works.mvcproject.models.Category;
import com.works.mvcproject.rest.models.category.ResultRest;
import com.works.mvcproject.rest.services.CategoryService;

@Controller
public class CategoryEditController {

	final CategoryService categoryService;

	public CategoryEditController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	int cid = 0;

	@GetMapping("/categoryEdit")
	public String categoryEdit(Model model) {
		cid = 0;
		model.addAttribute("categoryList", categoryService.categoryList());

		return "categoryEdit";
	}

	@PostMapping("/addCategory")
	public String categoryAdd(Category category) {
		if (cid != 0) {
			category.setCid(cid);
		}
		if (categoryService.findCategoryById(cid) != null) {
			categoryService.updateSingle(category.getCid(), category.getCategoryname(), category.getIconPath());
		} else {
			categoryService.insert(category.getCategoryname(), category.getIconPath());
		}

		cid = 0;

		return "redirect:/categoryEdit";
	}

	
	@PostMapping("/deleteCategory")
	public String deleteCategory(@RequestParam("scid") String scid) {

		try {			
			cid = Integer.parseInt(scid);
			categoryService.deleteSingle(cid);
		} catch (NumberFormatException e) {
			return "redirect:/";
		} catch (EmptyResultDataAccessException e) {
			return "redirect:/categoryEdit";
		}

		return "redirect:/categoryEdit";
	}

	String page = "";

	@GetMapping("/updateCategory/{scid}")
	public String updateCategory(@PathVariable String scid, Model model) {
		try {
			
			cid = Integer.parseInt(scid);
			ResultRest item = categoryService.findCategoryById(cid);

			model.addAttribute("update", item);
			model.addAttribute("categoryList", categoryService.categoryList());
			page = "categoryEdit";
		} catch (NumberFormatException e) {
			page = "redirect:/";
		}

		return page;
	}

	@GetMapping("/searchCategory")
	public String searchCategory(@RequestParam String searchTxt, Model model) {
		model.addAttribute("searchTxt", searchTxt);
		model.addAttribute("categoryList", categoryService.search(searchTxt));

		return "categoryEdit";
	}

}