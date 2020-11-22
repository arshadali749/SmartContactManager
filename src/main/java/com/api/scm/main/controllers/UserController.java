package com.api.scm.main.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepo userRepo;
	
	@RequestMapping("/dashboard")
	public String getDashBoard()
	{
		return"/normaluserpages/dash-board";
	}

}
