package com.api.scm.main.services;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.api.scm.main.entities.Contact;
import com.api.scm.main.entities.User;
import com.api.scm.main.repos.ContactRepo;
import com.api.scm.main.utils.Helper;
import com.api.scm.main.utils.ResponseMessage;

@Service
public class ContactService {
	@Autowired
	private ContactRepo contactRepo;
	@Autowired
	private Helper helper;
	@Autowired
	private HttpSession session;

//*******************************************************************************************
	public Contact saveContact(Contact contact, MultipartFile file, Principal principal) {

		try {
			// User user = userService.getUserByUserName(principal.getName());
			User user = helper.getCurrentActiveUser(principal);

			contact.setUser(user);
			if (!file.isEmpty()) {
				helper.uploadFile(file);
				contact.setImageURL(file.getOriginalFilename());
			} else {
				contact.setImageURL("profile-user.png");
			}

			System.out.println("IMAGE UPLOADED SUCCESSFULLY");
			contactRepo.save(contact);
			session.setAttribute("message",
					new ResponseMessage("Your contact has been added successfully..", "success"));

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new ResponseMessage("Sorry something went wrong...!!!", "danger"));

		}
		return contact;
	}

//***********************************************************************************
	public Page<Contact> getContactsList(Model model, int page, Principal principal) {
		User user = helper.getCurrentActiveUser(principal);
		model.addAttribute("title", "contacts list");
		Pageable pageableObject = PageRequest.of(page, 5);
		Page<Contact> contacts = contactRepo.findByUser(user, pageableObject);
		model.addAttribute("contacts", contacts);
		model.addAttribute("currentpage", page);
		model.addAttribute("totalpages", contacts.getTotalPages());
		return contacts;
	}

//**********************************************************************************
	public Optional<Contact> getContactById(int id) {
		return contactRepo.findById(id);
	}

	public void deleteContactById(int id, Principal principal) {

		Contact contact = contactRepo.getOne(id);
		User currentActiveUser = helper.getCurrentActiveUser(principal);
		// this line checks whether the contact that the user is trying to access
		// belongs to him or not if yes than the contact is passes in the model
		// otherwise not
		if (currentActiveUser.getId() == contact.getUser().getId())
			contactRepo.deleteById(id);

	}

//*********************************************************************************************
	public void updateContact(Contact contact, MultipartFile file, Principal principal) {

		try {
			// User user = userService.getUserByUserName(principal.getName());
			User user = helper.getCurrentActiveUser(principal);
			Contact oldContact = this.getContactById(contact.getId()).get();
			if (!file.isEmpty()) {
				helper.uploadFile(file);
				oldContact.setImageURL(file.getOriginalFilename());
				//code to delete the old image file will be placed here 
			}

			oldContact.setFirstName(contact.getFirstName());
			oldContact.setSecondName(contact.getSecondName());
			oldContact.setEmail(contact.getEmail());
			oldContact.setPhone(contact.getPhone());
			oldContact.setType(contact.getType());
			System.out.println("IMAGE UPLOADED SUCCESSFULLY");
			contactRepo.save(oldContact);
			session.setAttribute("message",
					new ResponseMessage("Your contact has been updated successfully..", "success"));

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new ResponseMessage("Sorry something went wrong...!!!", "danger"));

		}
	}

//*************************************************************************************************
	public Contact getContactDetails(int id, Model model, Principal principal) {

		Contact contact = contactRepo.getOne(id);
		User currentActiveUser = helper.getCurrentActiveUser(principal);
		// this line checks whether the contact that the user is trying to access
		// belongs to him or not if yes than the contact is passes in the model
		// otherwise not
		if (currentActiveUser.getId() == contact.getUser().getId())
			model.addAttribute("contact", contact);
		return contact;
	}
	
	public void updateContactById(Contact id) {
		
	}
}
