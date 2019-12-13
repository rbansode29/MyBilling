package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.Product;
import com.example.demo.services.crudservices.ProductService;

@Controller
public class ProductController {

	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping("/products")
	public String listProduct(Model model) {
		model.addAttribute("products", productService.listAllProduct());
		return "products";
	}

	@RequestMapping("/product/{id}")
	public String getProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "product";
	}

	@RequestMapping("/product/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "createProductForm";
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String saveOrUpdateProduct(Product product) {
		Product saveproduct = productService.saveOrUpdateProduct(product);
		return "redirect:/product/" + saveproduct.getId();
	}

	@RequestMapping("/product/edit/{id}")
	public String editProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "createProductForm";
	}

	@RequestMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Integer id, Model model) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}

	@RequestMapping("/product/home")
	public String goBackTOHome(Model model) {		
		return "redirect:/homePage";
	}
}
