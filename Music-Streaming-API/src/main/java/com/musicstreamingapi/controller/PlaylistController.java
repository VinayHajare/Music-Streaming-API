package com.musicstreamingapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musicstreamingapi.exception.PlaylistNotFoundException;
import com.musicstreamingapi.exception.SongNotFoundException;
import com.musicstreamingapi.model.Playlist;
import com.musicstreamingapi.model.Song;
import com.musicstreamingapi.service.PlaylistService;


@RestController
@RequestMapping("api/playlists")
public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	@PostMapping("/create")
	public ResponseEntity<Playlist> createPlaylist(@RequestParam String playlistName,
												   @RequestParam String username) {
		try {
			Playlist playlist = playlistService.createPlaylist(playlistName, username);
			return ResponseEntity.ok(playlist);
		}catch(UsernameNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/{playlistId}/addSong")
	public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable Long playlistId,
													  @RequestParam Long songId) {
		try {
			Playlist playlist = playlistService.addSongToPlaylist(playlistId, songId);
			return ResponseEntity.ok(playlist);
		}catch(PlaylistNotFoundException | SongNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{playlistId}/removeSong")
	public ResponseEntity<Playlist> removeSongFromPlaylist(@PathVariable Long playlistId, 
														   @RequestParam Long songId) {
		try {
			Playlist playlist = playlistService.removeSongFromPlaylist(playlistId, songId);
			return ResponseEntity.ok(playlist);
		} catch (PlaylistNotFoundException | SongNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<List<Playlist>> getUserPlaylists(@PathVariable String username) {
		try {
			List<Playlist> playlists = playlistService.getUserPlaylists(username);
			return ResponseEntity.ok(playlists);
		}catch (UsernameNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
    }
	
	@GetMapping("/{playlistId}")
	public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long playlistId) {
        Optional<Playlist> playlist = playlistService.getPlaylistById(playlistId);
        return playlist.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/{playlistId}/shuffle")
	public ResponseEntity<?> shufflePlaylist(@PathVariable Long playlistId) {
		try {
			Playlist playlist = playlistService.getPlaylistById(playlistId)
					.orElseThrow(() -> new PlaylistNotFoundException("Playlist not found with id :"+ playlistId));
			List<Song> shuffleSongs = playlistService.shufflePlaylist(playlist);
			return ResponseEntity.ok(shuffleSongs);
		} catch (PlaylistNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	
}
