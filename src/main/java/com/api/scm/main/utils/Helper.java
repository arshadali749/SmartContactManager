package com.api.scm.main.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.api.scm.main.entities.User;
import com.api.scm.main.services.UserService;

@Component
public class Helper {

	@Autowired
	private UserService userService;
	@Value("${IMAGES_PATH_URL}")
	private String IMAGES_PATH;

	public void uploadFile(MultipartFile file) {
		System.out.println("path: " + IMAGES_PATH);
		try {
			File pathToStoreImages = new ClassPathResource(IMAGES_PATH).getFile();
			Path imageDestinationPath = Paths
					.get(pathToStoreImages.getAbsolutePath() + File.separator + file.getOriginalFilename());

			Files.copy(file.getInputStream(), imageDestinationPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    
	public User getCurrentActiveUser(Principal principal) {
		return userService.getUserByUserName(principal.getName());
	}
}
