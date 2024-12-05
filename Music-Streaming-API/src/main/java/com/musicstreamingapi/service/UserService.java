package com.musicstreamingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.musicstreamingapi.dto.UserDTO;
import com.musicstreamingapi.exception.ArtistNotFoundException;
import com.musicstreamingapi.exception.EmailAlreadyExistsException;
import com.musicstreamingapi.model.Artist;
import com.musicstreamingapi.model.User;
import com.musicstreamingapi.repository.ArtistRepository;
import com.musicstreamingapi.repository.UserRepository;
import com.musicstreamingapi.util.LoggerUtil;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User registerNewUser(String username, String email, String password, String bio, String profilePicture) {
		if(userRepository.findByEmail(email).isPresent()) {
			LoggerUtil.logError("Email already exists!");
			throw new EmailAlreadyExistsException(email+" Email already exists!");	
		}
		
		String encodedPassword = passwordEncoder.encode(password);
		User user = new User(username, email, encodedPassword, bio, profilePicture, Collections.singleton("ROLE_USER"));
		
		return userRepository.save(user);
	}
	
	public UserDTO getUserProfile(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(user.getUsername());
		userDTO.setEmail(user.getEmail());
		userDTO.setBio(user.getBio());
		userDTO.setProfilePicture(user.getProfilePicture());
		
		return userDTO;
	}
	
	public UserDTO updateUserProfile(String username, UserDTO updatedProfile) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
		
		user.setBio(updatedProfile.getBio());
		user.setProfilePicture(updatedProfile.getProfilePicture());
		
		userRepository.save(user);
		
		return updatedProfile;
	}
	
	public Optional<User> getUserByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public User getCurrentUser(Authentication authentication) {
		if (authentication == null) {
			throw new IllegalStateException("Authentication object is required to retrieve current user.");
		}
		String username = authentication.getName();
		
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+ username));
	}
	
	public void followArtist(String username, Long artistId) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
		Artist artist = artistRepository.findById(artistId)
				.orElseThrow(() -> new ArtistNotFoundException("Artist not found with given ID"));
		user.getFollowedArtists().add(artist);
		userRepository.save(user);
	}
}
