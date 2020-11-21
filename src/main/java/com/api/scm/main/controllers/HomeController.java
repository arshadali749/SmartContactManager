package com.api.scm.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public String getHomePage(Model model) {
		model.addAttribute("title","index.html");
		return "index";
	}
	
	@RequestMapping("/about")
	public String getAboutPage(Model model) {
		model.addAttribute("title","about.html");
		return "about";
	}

}
