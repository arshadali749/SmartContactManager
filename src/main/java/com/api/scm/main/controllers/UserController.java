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

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {
		try {
			if (!agreement) {
				throw new Exception("you have not agreed with the terms and conditions");

			}
			user.setImageURL("profile-user.png");
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			User result = userRepo.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new ResponseMessage("Sucessfully Registered!!", "alert-success"));
			return "login";

		} catch (Exception e) {
			model.addAttribute("user", user);
			session.setAttribute("message",
					new ResponseMessage("Something went wrong" + e.getMessage(), "alert-danger"));
		}
		return "signup";
	}
}
