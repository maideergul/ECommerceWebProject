package com.works.mvcproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.works.mvcproject.rest.models.product.ResultRest;
import com.works.mvcproject.rest.services.CategoryService;
import com.works.mvcproject.rest.services.ProductService;

@Controller
public class SingleController {	
	
	final ProductService productService;
	final CategoryService categoryService;
	
	public SingleController(ProductService productService,CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}
	
	int spid = 0;

	@GetMapping("/single")
	public String single(Model model)
	{
		model.addAttribute("categoryList", categoryService.categoryList());
		model.addAttribute("productList", productService.productList());
		return "single";
	}	
	
	String page = "";
	@PostMapping("/detailProduct/{pid}")
	public String detailProduct(@PathVariable String pid, Model model)
	{		
		try
		{			
			spid  = Integer.parseInt(pid);
			ResultRest item = productService.findProductById(spid); 
			
			model.addAttribute("detail", item);
			model.addAttribute("productList", productService.productList());
			model.addAttribute("categoryList", categoryService.categoryList());
			
			page = "single";			
			
		} 
		catch (NumberFormatException e) 
		{			
			page = "redirect:/";
		}
		
		return page;
	}		

	@GetMapping("/cart/{pid}")
	public String cart(@PathVariable String pid, Model model)
	{		
		try {
			spid  = Integer.parseInt(pid);
			ResultRest item = productService.findProductById(spid); 			
				
				if(item.getPstatu() == 0)
				{		
					item.setPstatu(1);
					item.setQuantity(1);					
					productService.updateSingle(item.getPid(), item.getTitle(), item.getPrice(), item.getDetail(), item.getImg(), item.getCid(), item.getPstatu(), item.getQuantity());
				}
				else
				{
					item.setQuantity(item.getQuantity()+1);
					productService.updateSingle(item.getPid(), item.getTitle(), item.getPrice(), item.getDetail(), item.getImg(), item.getCid(), item.getPstatu(), item.getQuantity());
				}
				
				model.addAttribute("detail", item);
				model.addAttribute("productList", productService.productList());
				model.addAttribute("categoryList", categoryService.categoryList());
				model.addAttribute("cartCount", productService.cartProductCount());
				
				page = "single";
			
		} catch (NumberFormatException e) {			
			page = "redirect:/";
		}
		
		return page;
	}	
	
}