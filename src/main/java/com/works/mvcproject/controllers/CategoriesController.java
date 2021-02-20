package com.works.mvcproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.works.mvcproject.rest.models.category.ResultRest;
import com.works.mvcproject.rest.services.CategoryService;
import com.works.mvcproject.rest.services.ProductService;

@Controller
public class CategoriesController {
	
	final ProductService productService;
	final CategoryService categoryService;
	
	public CategoriesController(ProductService productService,CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}	
	
	@GetMapping("/categories")
	public String services(Model model)
	{
		model.addAttribute("productList", categoryService.categoryList());
		model.addAttribute("categoryList", categoryService.categoryList());
		model.addAttribute("cartCount", productService.cartProductCount());
		return "categories";
	}
	
	int scid=0;
	
	String page = "";
	@GetMapping("/category/{cid}")
	public String detailProduct(@PathVariable String cid, Model model)
	{		
		try {
				scid  = Integer.parseInt(cid);
			    ResultRest item = categoryService.findCategoryById(scid);	
			    
				model.addAttribute("detail", item); 
				model.addAttribute("productList", productService.findProductByCid(scid)); 
				model.addAttribute("categoryList", categoryService.categoryList());
				model.addAttribute("cartCount", productService.cartProductCount());
				
				page = "product";
				
		} catch (NumberFormatException e) {			
			page = "redirect:/";
		}
		
		return page;
	}
	
}