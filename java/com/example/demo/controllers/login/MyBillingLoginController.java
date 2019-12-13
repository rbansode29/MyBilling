package com.example.demo.controllers.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.User;
import com.example.demo.services.crudservices.UserService;
import com.example.demo.services.jpadaoservices.UserServiceJpaDaoImpl;

@Controller
public class MyBillingLoginController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOutUser(Model model) {
		return "redirect:/";
	}
	
	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public String showHomePage(Model model) {
		return "homePage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(User user) {
		String l_nextView = null;
		String l_userName = user.getUsername();
		String l_password = user.getPassword();

		if (isLoginSuccessful(l_userName, l_password)) {
			l_nextView = "/homePage";
		} else {
			l_nextView = "/";
		}
		return "redirect:"+ l_nextView;
	}

	private boolean isLoginSuccessful(final String p_userName, final String p_password) {
		// need to map DB connection and logic for validation
		boolean l_isLoginsuccessful = false;

		List<User> l_checkUser = userService.listAllUser();
		for (User user : l_checkUser) {
			boolean isPasswordSame = ((UserServiceJpaDaoImpl)userService).getEncryptionService().checkPassword(p_password, user.getEncryptedpassword());
			if (user.getUsername().equals(p_userName) && isPasswordSame ) {
				l_isLoginsuccessful = true;
			} else {
				l_isLoginsuccessful = false;
			}
		}

		return l_isLoginsuccessful;
	}

}
