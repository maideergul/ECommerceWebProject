package com.works.mvcproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.works.mvcproject.rest.services.CategoryService;
import com.works.mvcproject.rest.services.ProductService;

@Controller
public class AboutController {
	
	final CategoryService categoryService;
	final ProductService productService;
	
	public AboutController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@GetMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("categoryList", categoryService.categoryList());
		model.addAttribute("cartCount", productService.cartProductCount());
		return "about";
	}	

}