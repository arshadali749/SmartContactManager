package com.api.scm.main.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.api.scm.main.entities.Contact;
import com.api.scm.main.entities.User;
import com.api.scm.main.services.ContactService;
import com.api.scm.main.services.UserService;
import com.api.scm.main.utils.Helper;
import com.sun.xml.bind.api.impl.NameConverter.Standard;

@Controller
@RequestMapping("/contacts/")
public class ContactController {
	@Autowired
	private UserService userService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private Helper helper;

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
	public String addNewContact(@ModelAttribute Contact contact, @RequestParam("imageFile") MultipartFile file,
			Model model, Principal principal) {

		try {
			// User user = userService.getUserByUserName(principal.getName());
			User user = helper.getCurrentActiveUser(principal);

			contact.setUser(user);
			if (file.isEmpty()) {
				System.out.println("No imaged for the contact is selected");

			} else {
				helper.uploadFile(file);
				contact.setImageURL(file.getOriginalFilename());
				System.out.println("IMAGE UPLOADED SUCCESSFULLY");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		contactService.saveContact(contact);
		return "normaluserpages/add-contact-form";
	}

}
