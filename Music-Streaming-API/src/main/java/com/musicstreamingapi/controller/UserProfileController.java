package com.musicstreamingapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicstreamingapi.dto.UserDTO;
import com.musicstreamingapi.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/profile")
public class UserProfileController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{username}")
	public ResponseEntity<UserDTO> getProfile(@PathVariable String username) {
		try {
			UserDTO userDTO = userService.getUserProfile(username);
			return ResponseEntity.ok(userDTO);
		}catch(UsernameNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<UserDTO> updateProfile(@PathVariable String username, 
												 @RequestBody UserDTO updatedProfile) {
		try {
			UserDTO userDTO = userService.updateUserProfile(username, updatedProfile);
			return ResponseEntity.ok(userDTO);
		}catch(UsernameNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
