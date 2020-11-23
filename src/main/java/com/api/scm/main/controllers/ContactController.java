package com.api.scm.main.controllers;

import java.io.File;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.scm.main.entities.Contact;
import com.api.scm.main.entities.User;
import com.api.scm.main.services.ContactService;
import com.api.scm.main.services.UserService;

@Controller
@RequestMapping("/contacts/")
public class ContactController {
	@Autowired
	private UserService userService;
	@Autowired
	private ContactService contactService;

	// this will contain the all the data which is common , all the pages can access
	// it and will be included in the response of each in to this controller
	@ModelAttribute
	public void currentUser(Model model, Principal principal) {
		String CurrentUserName = principal.getName();
		User currentUser = userService.getUserByUserName(CurrentUserName);
		model.addAttribute("user", currentUser);

	}

	@GetMapping("/new")
	public String getAddContactForm(Model model) {

		return "normaluserpages/add-contact-form";
	}

	@PostMapping("/add")
	public String addNewContact(@ModelAttribute Contact contact, @RequestParam File imageFile, Model model,
			Principal principal) {
		User user = userService.getUserByUserName(principal.getName());
		contact.setUser(user);
		contactService.saveContact(contact);
		return "normaluserpages/add-contact-form";
	}

}
