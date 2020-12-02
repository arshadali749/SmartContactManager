package com.api.scm.main.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.api.scm.main.entities.Contact;
import com.api.scm.main.entities.User;
import com.api.scm.main.services.ContactService;
import com.api.scm.main.services.UserService;
import com.api.scm.main.utils.Helper;
import com.api.scm.main.utils.ResponseMessage;

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

	@GetMapping("/list/{page}") // here page represents the current page
	public String showContactsList(@PathVariable int page, Model model, Principal principal) {
		model.addAttribute("title", "contacts list");
		Pageable pageable = PageRequest.of(page, 6);
		Page<Contact> contacts = contactService.getContactsList(helper.getCurrentActiveUser(principal), pageable);
		model.addAttribute("contacts", contacts);
		model.addAttribute("currentpage", page);
		model.addAttribute("totalpages", contacts.getTotalPages());
		return "normaluserpages/contacts";
	}

	@GetMapping("/new")
	public String getAddContactForm(Model model) {

		return "normaluserpages/add-contact-form";
	}

	@PostMapping("/add")
	public String addNewContact(@ModelAttribute Contact contact, @RequestParam("imageFile") MultipartFile file,
			Model model, Principal principal, HttpSession session) {

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
				contactService.saveContact(contact);
				session.setAttribute("message",
						new ResponseMessage("Your contact has been added successfully..", "success"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new ResponseMessage("Sorry something went wrong...!!!", "danger"));

		}

		return "redirect:/contacts/list/0";
	}

}
