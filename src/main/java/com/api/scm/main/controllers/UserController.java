package com.api.scm.main.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.scm.main.entities.User;
import com.api.scm.main.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Value("${path}")
	private  String PATH;
	@Autowired
	private UserService userservice;

	// this will contain the all the data which is common , all the pages can access
	// it and will be included in the response of each in to this controller
	@ModelAttribute
	public void getCUrrentUser(Model model, Principal principal) {
		String CurrentUserName = principal.getName();
		System.out.println("User:" + CurrentUserName);
		User currentUser = userservice.getUserByUserName(CurrentUserName);
		System.out.println("CURRENT USER:" + currentUser);
		model.addAttribute("user", currentUser);
	}

	@RequestMapping("/dashboard")
	public String getDashBoard(Model model)

	{
		model.addAttribute("title", "dashnoard");
		return "/normaluserpages/dash-board";
	}

	@RequestMapping("/new-contact")
	public String getAddContactForm() {
		return "normaluserpages/add-contact-form";
	}

}
