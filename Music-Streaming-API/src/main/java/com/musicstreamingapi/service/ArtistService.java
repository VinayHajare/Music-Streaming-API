package com.musicstreamingapi.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicstreamingapi.dto.ArtistDTO;
import com.musicstreamingapi.exception.ArtistNotFoundException;
import com.musicstreamingapi.model.Artist;
import com.musicstreamingapi.model.User;
import com.musicstreamingapi.repository.ArtistRepository;

@Service
public class ArtistService {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	public Artist createArtist(Artist artist) {
		return artistRepository.save(artist);
	}
	
	public List<ArtistDTO> getAllArtist() {
		return artistRepository.findAll().stream()
				.map(artist -> new ArtistDTO(
						artist.getId(), 
						artist.getName(), 
						artist.getGenre(), 
						artist.getFollowers().stream()
						.map(this::mapFollowers)
						.collect(Collectors.toList())
				))
				.collect(Collectors.toList());
	}
	
	public ArtistDTO getArtistById(Long id) {
		Artist artist = artistRepository.findById(id)
	            .orElseThrow(() -> new ArtistNotFoundException("Artist not found with " + id + " Id"));
		
		List<Map<String, Object>> followers = artist.getFollowers().stream()
				.map(this::mapFollowers)
				.collect(Collectors.toList());
		
		return new ArtistDTO(artist.getId(), artist.getName(), artist.getGenre(), followers);
	}
	
	public List<ArtistDTO> searchArtistByName(String name) {
		return artistRepository.findByNameContainingIgnoreCase(name).stream()
				.map(artist -> new ArtistDTO(
						artist.getId(),
						artist.getName(),
						artist.getGenre(),
						artist.getFollowers().stream()
						.map(this::mapFollowers)
						.collect(Collectors.toList())
				))
				.collect(Collectors.toList());
	}
	
	private Map<String, Object> mapFollowers(User follower) { 
		return Map.of(
				"Id", follower.getId(),
				"username", follower.getUsername()
		);
	}
}
