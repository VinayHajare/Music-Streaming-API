package com.musicstreamingapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.musicstreamingapi.dto.ArtistDTO;
import com.musicstreamingapi.exception.ArtistNotFoundException;
import com.musicstreamingapi.model.Artist;
import com.musicstreamingapi.service.ArtistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("api/artists")
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@GetMapping
	public ResponseEntity<List<ArtistDTO>> getAllArtists() {
		return ResponseEntity.ok(artistService.getAllArtist());
	}
	
	@PostMapping
	public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
		Artist createdArtist = artistService.createArtist(artist);
		return ResponseEntity.ok(createdArtist);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getArtistById(@PathVariable Long id) {
		try {
			ArtistDTO artistDTO = artistService.getArtistById(id);
			return ResponseEntity.ok(artistDTO);
		} catch (ArtistNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ArtistDTO>> searchArtistByName(@RequestParam String name) {
		return ResponseEntity.ok(artistService.searchArtistByName(name));
	}
	
}
