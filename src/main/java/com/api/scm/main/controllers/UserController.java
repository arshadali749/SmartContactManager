package com.api.scm.main.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.scm.main.entities.User;
import com.api.scm.main.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userservice;

	@RequestMapping("/dashboard")
	public String getDashBoard(Model model, Principal principal)

	{
		String CurrentUserName = principal.getName();
		System.out.println("User:" + CurrentUserName);
		User currentUser = userservice.getUserByUserName(CurrentUserName);
		System.out.println("CURRENT USER:" + currentUser);
		model.addAttribute("user",currentUser);
		return "/normaluserpages/dash-board";
	}

}
