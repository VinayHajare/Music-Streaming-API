package com.musicstreamingapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicstreamingapi.exception.EmailAlreadyExistsException;
import com.musicstreamingapi.model.User;
import com.musicstreamingapi.service.UserService;
import com.musicstreamingapi.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody User user) {
		try {
			User newUser = userService.registerNewUser(user.getUsername(), user.getEmail(), user.getPassword(), user.getBio(), user.getProfilePicture());
			return ResponseEntity.ok("User registered successfully!");
		}catch(EmailAlreadyExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		Optional<User> existingUser = userService.getUserByEmail(user.getEmail());
		
		if(existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
			String token = jwtUtil.generateToken(existingUser.get().getUsername());
			return ResponseEntity.ok(token);
		}else {
			return ResponseEntity.status(401).body("Invalid credentials");
		}
	}
}
