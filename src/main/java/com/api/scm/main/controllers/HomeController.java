package com.api.scm.main.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.scm.main.entities.User;
import com.api.scm.main.repos.UserRepo;
import com.api.scm.main.utils.ResponseMessage;

@Controller
public class HomeController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@RequestMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("title", "index.html");
		return "index";
	}

	@RequestMapping("/about")
	public String getAboutPage(Model model) {
		model.addAttribute("title", "about.html");
		return "about";
	}

	@RequestMapping("/signup")
	public String getSignUpPage(Model model) {
		model.addAttribute("title", "signup.html");
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping("/login") 
	public String getLoginPage(Model model) { 
		model.addAttribute("title", "login.html");
		return "login";
	} 
	
	
	@RequestMapping("/logout") 
	public String logout(Model model) { 
		model.addAttribute("title", "login.html");
		return "login";
	} 

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {
		try {
			if (!agreement) {
				throw new Exception("you have not agreed with the terms and conditions");

			}
			user.setPassword(encoder.encode(user.getPassword()));
			user.setImageURL("profile-user.png");
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			User result = userRepo.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new ResponseMessage("Sucessfully Registered!!", "alert-success"));
			return "login";

		} catch (Exception e) {
			model.addAttribute("user", user);
			session.setAttribute("message", new ResponseMessage("e.getMessage()", "alert-danger"));
		} 
		return "signup";
	}

}
