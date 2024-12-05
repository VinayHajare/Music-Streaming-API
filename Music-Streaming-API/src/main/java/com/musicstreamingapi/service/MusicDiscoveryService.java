package com.musicstreamingapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.musicstreamingapi.model.Song;
import com.musicstreamingapi.model.User;
import com.musicstreamingapi.repository.SongRepository;
import com.musicstreamingapi.repository.UserRepository;

@Service
public class MusicDiscoveryService {
	
	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Song> recommendSongsByGenre(String genre) {
		return songRepository.findByGenreContainingIgnoreCase(genre);
	}
	
	public List<Song> recommendSongsForUser(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
		
		// This is simple recommendation based on the first followed artist's genre
		if (user.getFollowedArtists().isEmpty()) {
			return songRepository.findAll(); // Return all songs if user follows no artists
		}
		
		String genre = user.getFollowedArtists().iterator().next().getGenre();
		return songRepository.findByGenreContainingIgnoreCase(genre);
	}
}
