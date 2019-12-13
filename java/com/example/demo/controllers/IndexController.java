package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.User;

@Controller
public class IndexController {

	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}
	
	@RequestMapping(value ="/createUser", method=RequestMethod.GET)
	public String createNewUser(Model model) {
		return "redirect:/user/new";
	}
}
