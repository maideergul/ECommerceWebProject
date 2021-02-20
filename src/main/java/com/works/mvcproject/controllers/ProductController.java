package com.works.mvcproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.works.mvcproject.rest.models.product.ResultRest;
import com.works.mvcproject.rest.services.CategoryService;
import com.works.mvcproject.rest.services.ProductService;

@Controller
public class ProductController {	

	final ProductService productService;
	final CategoryService categoryService;

	
	public ProductController(ProductService productService,CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}
	
	int spid=0;

	@GetMapping("/product")
	public String product(Model model)
	{			
		model.addAttribute("productList", productService.productList());	
		model.addAttribute("categoryList",categoryService.categoryList());
		model.addAttribute("cartCount", productService.cartProductCount());		
		
		return "product";
	}
	
	String page = "";
	@GetMapping("/detail/{pid}")
	public String detailProduct(@PathVariable String pid, Model model)
	{		
		try {
			spid  = Integer.parseInt(pid);
			 ResultRest item = productService.findProductById(spid); 
			
				model.addAttribute("detail", item);
				model.addAttribute("productList", productService.productList());
				model.addAttribute("categoryList", categoryService.categoryList());
				page = "single";
			
		} catch (NumberFormatException e) {			
			page = "redirect:/";
		}
		
		return page;
	}
	
	@GetMapping("/carts/{pid}")
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
				}else
				{
					item.setQuantity(item.getQuantity()+1);
					productService.updateSingle(item.getPid(), item.getTitle(), item.getPrice(), item.getDetail(), item.getImg(), item.getCid(), item.getPstatu(), item.getQuantity());
				}
				
				model.addAttribute("detail", item);
				model.addAttribute("productList", productService.productList());
				model.addAttribute("categoryList", categoryService.categoryList());
				page = "redirect:/product";
			
		} catch (NumberFormatException e) {			
			page = "redirect:/";
		}
		
		return page;
	}	

}