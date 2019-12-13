package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.Customer;
import com.example.demo.services.crudservices.CustomerService;

@Controller
public class CustomerController {

	public CustomerController() {
		// TODO Auto-generated constructor stub
	}

	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping("/customers")
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerService.listAllCustomer());
		return "customers";
	}

	@RequestMapping("/customer/{id}")
	public String getCustomer(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "customer";
	}

	@RequestMapping("/customer/new")
	public String newCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "createCustomerForm";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public String saveOrUpdateCustomer(Customer customer) {
		Customer saveCustomer = customerService.saveOrUpdateCustomer(customer);
		return "redirect:/customer/" + saveCustomer.getId();
	}

	@RequestMapping("/customer/edit/{id}")
	public String editCustomer(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "createCustomerForm";
	}

	@RequestMapping("/customer/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id, Model model) {
		customerService.deleteCustomer(id);
		return "redirect:/customers";
	}

	@RequestMapping("/customer/homePage")
	public String goBackTOHome(Model model) {		
		return "redirect:/homePage";
	}
}
