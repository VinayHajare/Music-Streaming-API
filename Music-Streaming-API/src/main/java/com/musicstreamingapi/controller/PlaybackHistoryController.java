package com.musicstreamingapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicstreamingapi.dto.PlaybackHistoryDTO;
import com.musicstreamingapi.model.User;
import com.musicstreamingapi.service.PlaybackHistoryService;
import com.musicstreamingapi.service.UserService;


@RestController
@RequestMapping("api/playback-history")
public class PlaybackHistoryController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlaybackHistoryService playbackHistoryService;
	
	@GetMapping
	public ResponseEntity<?> getPlaybackHistory(Authentication authentication) {
		try {
				User user = userService.getCurrentUser(authentication);
				List<PlaybackHistoryDTO> playbackHistory = playbackHistoryService.getPlaybackHistory(user);
				return ResponseEntity.ok(playbackHistory);
		} catch (UsernameNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	
}
