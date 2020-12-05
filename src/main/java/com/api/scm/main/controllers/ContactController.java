package com.api.scm.main.controllers;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/list/{page}") // here page represents the current page
	public String showContactsList(@PathVariable int page, Model model, Principal principal) {
		contactService.getContactsList(model, page, principal);
		return "normaluserpages/contacts";
	}

	@GetMapping("/new")
	public String getAddContactForm(Model model) {

		return "normaluserpages/add-contact-form";
	}

	@PostMapping("/add")
	public String addNewContact(@ModelAttribute Contact contact, @RequestParam("imageFile") MultipartFile file,
			Model model, Principal principal, HttpSession session) {
		contactService.saveContact(contact, file, principal);
		return "redirect:/contacts/list/0";
	}

	@RequestMapping("/detail/{id}")
	public String showDetail(@PathVariable int id, Model model, Principal principal) {

		return "normaluserpages/contact-details";

	}

	@RequestMapping("/delete/{id}")
	public String deleteContact(@PathVariable int id, Principal principal) {

		contactService.deleteContactById(id, principal);
		return "redirect:/contacts/list/0";

	}

	@PostMapping("/edit/{id}")
	public String editContact(Model model, @PathVariable int id) {
		Optional<Contact> optionalContact = contactService.getContactById(id);
		Contact contact = optionalContact.get();
		model.addAttribute("contact", contact);
		model.addAttribute("title", "edit contact");
		return "normaluserpages/edit-contact-form";
	}

	// contact update handler
	@PostMapping("/update")
	public String updateContact(@ModelAttribute Contact contact, @RequestParam("imageFile") MultipartFile file,
			Model model, Principal principal, HttpSession session) {
		System.out.println("UPDATE CALLED");
		System.out.println("COntact to be updated:" + contact);
		contactService.updateContact(contact, file, principal);
		return "redirect:/contacts/list/0";

	}

}
