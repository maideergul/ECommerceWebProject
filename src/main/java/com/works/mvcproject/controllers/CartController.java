package com.works.mvcproject.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.works.mvcproject.rest.models.product.ResultRest;
import com.works.mvcproject.rest.services.CartProcService;
import com.works.mvcproject.rest.services.CategoryService;
import com.works.mvcproject.rest.services.ProductService;

@Controller
public class CartController {

	final CategoryService categoryService;
	final CartProcService cartProcService;
	final ProductService productService;

	public CartController(CartProcService cartProcService, ProductService productService,
			CategoryService categoryService) {
		this.categoryService = categoryService;
		this.cartProcService = cartProcService;
		this.productService = productService;
	}

	@GetMapping("/cart")
	public String cart(Model model) {
		int cartTotalAmount;

		if (productService.cartProductCount() > 0) {
			cartTotalAmount = productService.cartTotalAmount();
		} else {
			cartTotalAmount = 0;
		}

		model.addAttribute("categoryList", categoryService.categoryList());
		model.addAttribute("productList", cartProcService.restResult());
		model.addAttribute("totalByProduct", productService.totalPriceOfProducts());
		model.addAttribute("cartCount", productService.cartProductCount());
		model.addAttribute("totalAmount", cartTotalAmount);

		return "cart";
	}

	int stPid = 0;
	String page = "";

	@GetMapping("/cartremove/{pid}")
	public String cart(@PathVariable String pid, Model model) {
		
		model.addAttribute("productList", productService.productList());
		model.addAttribute("categoryList", categoryService.categoryList());

		try {
			stPid = Integer.parseInt(pid);
			ResultRest item = productService.findProductById(stPid);
			if (item.getPstatu() == 1) {
				item.setPstatu(0);
				item.setQuantity(0);
				productService.updateSingle(item.getPid(), item.getTitle(), item.getPrice(), item.getDetail(),
						item.getImg(), item.getCid(), item.getPstatu(), item.getQuantity());
				model.addAttribute("detail", item);

			}
			page = "redirect:/cart";

		} catch (NumberFormatException e) {
			page = "redirect:/";
		}

		return page;
	}

	@GetMapping("/quantityDecrease/{pid}")
	public String quantityDecrease(@PathVariable String pid, Model model) {
		
		try {
			
			stPid = Integer.parseInt(pid);
			ResultRest item = productService.findProductById(stPid);

			if (item.getQuantity() >= 1) 
			{
				item.setQuantity(item.getQuantity() - 1);
				productService.updateSingle(item.getPid(), item.getTitle(), item.getPrice(), item.getDetail(),item.getImg(), item.getCid(), item.getPstatu(), item.getQuantity());
				
				if (item.getQuantity() == 0) 
				{
					item.setPstatu(0);
					productService.updateSingle(item.getPid(), item.getTitle(), item.getPrice(), item.getDetail(),item.getImg(), item.getCid(), item.getPstatu(), item.getQuantity());
				}
			}
			
			model.addAttribute("detail", item);
			model.addAttribute("productList", productService.productList());
			model.addAttribute("categoryList", categoryService.categoryList());
			page = "redirect:/cart";

		} catch (NumberFormatException e) {
			page = "redirect:/";
		}

		return page;
	}

	@GetMapping("/quantityIncrease/{pid}")
	public String quantityIncrease(@PathVariable String pid, Model model) {
		try {
			
			stPid = Integer.parseInt(pid);
			ResultRest item = productService.findProductById(stPid);

			item.setQuantity(item.getQuantity() + 1);
			productService.updateSingle(item.getPid(), item.getTitle(), item.getPrice(), item.getDetail(),item.getImg(), item.getCid(), item.getPstatu(), item.getQuantity());

			model.addAttribute("detail", item);
			model.addAttribute("productList", productService.productList());
			model.addAttribute("categoryList", categoryService.categoryList());
			page = "redirect:/cart";

		} catch (NumberFormatException e) {
			page = "redirect:/";
		}

		return page;
	}

	@GetMapping("/checkout")
	public String checkout(Model model) {
		try {

			List<ResultRest> plist = productService.productsInCart();
			for (ResultRest resultRest : plist) 
			{
				resultRest.setPstatu(0);
				productService.updateSingle(resultRest.getPid(), resultRest.getTitle(), resultRest.getPrice(),resultRest.getDetail(), resultRest.getImg(), resultRest.getCid(), resultRest.getPstatu(),	resultRest.getQuantity());
			}
			page = "redirect:/cart";
			
		} catch (NumberFormatException e) {
			page = "redirect:/";
		}

		return page;
	}

}