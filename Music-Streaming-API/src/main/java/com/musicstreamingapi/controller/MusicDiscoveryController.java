package com.musicstreamingapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musicstreamingapi.model.Song;
import com.musicstreamingapi.model.User;
import com.musicstreamingapi.service.MusicDiscoveryService;
import com.musicstreamingapi.service.RecommendationService;
import com.musicstreamingapi.service.UserService;



@RestController
@RequestMapping("api/discover")
public class MusicDiscoveryController {
	
	@Autowired
	private MusicDiscoveryService musicDiscoveryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RecommendationService recommendationService;
	
	@GetMapping("/recommend/genre")
	public ResponseEntity<List<Song>> recommendByGenre(@RequestParam String genre) {
		List<Song> songs = musicDiscoveryService.recommendSongsByGenre(genre);
		return ResponseEntity.ok(songs);
	}
	
	@GetMapping("/recommend/user/{username}")
	public ResponseEntity<?> recommendForUser(@PathVariable String username) {
		try {
			List<Song> songs = musicDiscoveryService.recommendSongsForUser(username);
			return ResponseEntity.ok(songs);
		} catch(UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/recommendations")
	public ResponseEntity<?> getRecommendations(Authentication authentication) {
		try {
			User user = userService.getCurrentUser(authentication);
			List<Song> recommendations = recommendationService.getRecommendations(user);
			return ResponseEntity.ok(recommendations);
		} catch(UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	
	@PostMapping("/followArtist")
	public ResponseEntity<String> followArtist(@RequestParam String username,
							   				   @RequestParam Long artistId) {
		
		userService.followArtist(username, artistId);
		return ResponseEntity.ok("Artist followed successfully");
	}
}
