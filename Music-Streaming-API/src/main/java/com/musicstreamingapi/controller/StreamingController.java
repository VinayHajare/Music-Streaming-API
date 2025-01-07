package com.musicstreamingapi.controller;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicstreamingapi.exception.SongNotFoundException;
import com.musicstreamingapi.model.Song;
import com.musicstreamingapi.model.User;
import com.musicstreamingapi.service.PlaybackHistoryService;
import com.musicstreamingapi.service.SongService;
import com.musicstreamingapi.service.UserService;

import io.jsonwebtoken.io.IOException;


@RestController
@RequestMapping("api/stream")
public class StreamingController {
	
	@Autowired
	private SongService songService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlaybackHistoryService playbackHistoryService;
	
	@GetMapping("/play/{songId}")
	public ResponseEntity<?> streamSong(@PathVariable Long songId, Authentication authentication) {
	    try {
	        Song song = songService.getSongById(songId)
	        		.orElseThrow(() -> new SongNotFoundException("Song with ID " + songId + " not found."));
	        User user = userService.getCurrentUser(authentication);
	        Resource resource = new UrlResource(song.getStreamUrl());

	        if (!resource.exists() || !resource.isReadable()) {
                throw new MalformedURLException("The song URL is invalid or not readable.");
            }
	        
	        // Log playback history
	        playbackHistoryService.logPlayback(user, song);
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + resource.getFilename() + "\"");
	        	        
	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .headers(headers)
	                .body(resource);
	        
	    } catch (SongNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (IOException ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error streaming song.");
	    } catch (MalformedURLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while loading song. ERROR::"+e.getMessage());
		}
	}
	
}
