package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.User;
import com.example.demo.services.crudservices.UserService;

@Controller
public class UserController {
	
	private UserService userService;

	public UserController() {
		super();
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/users")
	public String listUser(Model model) {
		model.addAttribute("users", userService.listAllUser());
		return "users";
	}

	@RequestMapping("/user/{id}")
	public String getUser(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "user";
	}

	@RequestMapping("/user/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "createUserForm";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String saveOrUpdateUser(User user) {
		User savedUser = userService.saveOrUpdateUser(user);
		return "redirect:/";
	}

	@RequestMapping("/user/edit/{id}")
	public String editUser(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "createUserForm";
	}

	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable Integer id, Model model) {
		userService.deleteUser(id);
		return "redirect:/users";
	}
	
	@RequestMapping("/user/homePage")
	public String goBackTOHome(Model model) {		
		return "redirect:/homePage";
	}
}
