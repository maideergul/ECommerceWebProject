package com.works.mvcproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.works.mvcproject.rest.models.product.ResultRest;
import com.works.mvcproject.rest.services.CategoryService;
import com.works.mvcproject.rest.services.ProductCidViewService;
import com.works.mvcproject.rest.services.ProductService;

@Controller
public class IndexController {

	final CategoryService categoryService;
	final ProductCidViewService productCidViewService;
	final ProductService productService;

	public IndexController(ProductCidViewService productCidViewService, ProductService productService,CategoryService categoryService) {

		this.productCidViewService = productCidViewService;
		this.productService = productService;
		this.categoryService = categoryService;
	}

	int spid = 0;

	@GetMapping("/index")
	public String index(Model model) {
		
		model.addAttribute("productList", productService.productList());
		model.addAttribute("categoryList", categoryService.categoryList());
		model.addAttribute("cartCount", productService.cartProductCount());
		return "index";
	}

	String page = "";

	@GetMapping("/detailProduct/{pid}")
	public String detailProduct(@PathVariable String pid, Model model) {
		
		try {
			spid = Integer.parseInt(pid);
			ResultRest item = productService.findProductById(spid);
			model.addAttribute("detail", item);
			model.addAttribute("productList", productService.productList());
			model.addAttribute("categoryList", categoryService.categoryList());
			model.addAttribute("cartCount", productService.cartProductCount());

			page = "single";
		} 
		catch (NumberFormatException e) 
		{
			page = "redirect:/";
		}

		return page;
	}

	@GetMapping("/addToCart/{pid}")
	public String cart(@PathVariable String pid, Model model) {
		try {
			spid = Integer.parseInt(pid);
			ResultRest item = productService.findProductById(spid);

			if (item.getPstatu() == 0) 
			{
				item.setPstatu(1);
				item.setQuantity(1);
				productService.updateSingle(item.getPid(), item.getTitle(), item.getPrice(), item.getDetail(),item.getImg(), item.getCid(), item.getPstatu(), item.getQuantity());
			} 
			else 
			{				
				item.setQuantity(item.getQuantity() + 1);
				productService.updateSingle(item.getPid(), item.getTitle(), item.getPrice(), item.getDetail(),item.getImg(), item.getCid(), item.getPstatu(), item.getQuantity());
			}

			model.addAttribute("detail", item);
			model.addAttribute("productList", productService.productList());
			model.addAttribute("categoryList", categoryService.categoryList());
			
			page = "redirect:/index";

		} 
		catch (NumberFormatException e) 
		{
			page = "redirect:/";
		}

		return page;
	}

	@GetMapping("/search")
	public String search(@RequestParam String searchTxt, Model model)
	{
		if (searchTxt.equals(""))
		{
			return "redirect:/index";
		}

		model.addAttribute("searchTxt", searchTxt);
		model.addAttribute("categoryList", categoryService.categoryList());
		model.addAttribute("cartCount", productService.cartProductCount());
		model.addAttribute("productList", productCidViewService.search(searchTxt));
		
		return "product";
	}

}