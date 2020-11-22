package com.api.scm.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.scm.main.entities.User;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("title","index.html");
		return "index";
	}
	
	@RequestMapping("/about")
	public String getAboutPage(Model model) {
		model.addAttribute("title","about.html");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String getSignUpPage(Model model) {
		model.addAttribute("title","signup.html");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@RequestMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("title","login.html");
		return "login";
	}

}
