package com.works.mvcproject.controllers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.works.mvcproject.models.Product;
import com.works.mvcproject.rest.models.product.ResultRest;
import com.works.mvcproject.rest.services.CategoryService;
import com.works.mvcproject.rest.services.ProductCidViewService;
import com.works.mvcproject.rest.services.ProductService;

@Controller
public class ProductEditController {
	
	final ProductService productService;
	final CategoryService categoryService;
	final ProductCidViewService productCidViewService;

	public ProductEditController(ProductService productService,ProductCidViewService productCidViewService,CategoryService categoryService) {		
		
		this.productService = productService;
		this.categoryService = categoryService;
		this.productCidViewService = productCidViewService;
	}

	int pid = 0;

	@GetMapping("/productEdit")
	public String productEdit(Model model) {
		pid = 0;
		model.addAttribute("pcvlist", productCidViewService.restResult());
		model.addAttribute("categoryList", categoryService.categoryList());

		return "productEdit";
	}

	@PostMapping("/addProduct")
	public String productAdd(Product product,Model model) {
		
		model.addAttribute("categoryList", categoryService.categoryList());
		if (pid != 0) {
			product.setPid(pid);
		}
		if(productService.findProductById(pid) != null)
		{
			productService.updateSingle(product.getPid(), product.getTitle(), product.getPrice(), product.getDetail(), product.getImg(), product.getCid(), product.getPstatu(), product.getQuantity());
		}else
		{
			productService.insert(product.getTitle(), product.getPrice(), product.getDetail(), product.getImg(), product.getCid(), product.getPstatu(), product.getQuantity());
		}
		pid = 0;

		return "redirect:/productEdit";
	}	
	
	@PostMapping("/deleteProduct")
	public String productDelete(@RequestParam("spid") String spid,Model model)
	{		
		model.addAttribute("categoryList", categoryService.categoryList());

		try {
			pid  = Integer.parseInt(spid);
			productService.deleteSingle(pid);
		} catch (NumberFormatException e) {
			return "redirect:/";
		}catch (EmptyResultDataAccessException e) {
			return "redirect:/productEdit";
		}
		
		return "redirect:/productEdit";
	}	

	String page = "";

	@GetMapping("/updateProduct/{spid}")
	public String updateProduct(@PathVariable String spid, Model model) {
		model.addAttribute("categoryList", categoryService.categoryList());

		
		try 
		{			
			pid = Integer.parseInt(spid);
			ResultRest item = productService.findProductById(pid);
			
			model.addAttribute("update", item);
			model.addAttribute("productList", productService.productList());
			model.addAttribute("categoryList",categoryService.categoryList());
			
			page = "productEdit";			
		} 
		catch (NumberFormatException e)
		{
			page = "redirect:/";
		}

		return page;
	}

	@GetMapping("/searchData")
	public String searchData(@RequestParam String searchTxt, Model model) {
		
		model.addAttribute("categoryList", categoryService.categoryList());
		model.addAttribute("searchTxt", searchTxt);
		model.addAttribute("pcvlist",productCidViewService.search(searchTxt)); 
		return "productEdit";
	}

}