package com.works.mvcproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.works.mvcproject.rest.services.CategoryService;
import com.works.mvcproject.rest.services.ProductService;

@Controller
public class ContactController {
	
	final CategoryService categoryService;
	final ProductService productService;
	
	public ContactController(CategoryService categoryService,ProductService productService) {
		
		this.categoryService = categoryService;
		this.productService = productService;
	}

	@GetMapping("/contact")
	public String contact(Model model)
	{
		model.addAttribute("categoryList", categoryService.categoryList());
		model.addAttribute("cartCount", productService.cartProductCount());
		return "contact";
	}
	
}