package com.musicstreamingapi.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.musicstreamingapi.exception.PlaylistNotFoundException;
import com.musicstreamingapi.exception.SongNotFoundException;
import com.musicstreamingapi.model.Playlist;
import com.musicstreamingapi.model.Song;
import com.musicstreamingapi.model.User;
import com.musicstreamingapi.repository.PlaylistRepository;
import com.musicstreamingapi.repository.SongRepository;
import com.musicstreamingapi.repository.UserRepository;

@Service
public class PlaylistService {
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Playlist createPlaylist(String playlistName, String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
		
		Playlist playlist = new Playlist();
		playlist.setName(playlistName);
		playlist.setUser(user);
		return playlistRepository.save(playlist);
	}
	
	public Playlist addSongToPlaylist(Long playlistId, Long songId) {
		Playlist playlist = playlistRepository.findById(playlistId)
				.orElseThrow(() -> new PlaylistNotFoundException("Playlist not found"));
		
		Song song = songRepository.findById(songId)
				.orElseThrow(() -> new SongNotFoundException("Song not found"));
		
		playlist.getSongs().add(song);
		return playlistRepository.save(playlist);
	}
	
	public Playlist removeSongFromPlaylist(Long playlistId, Long songId) {
		Playlist playlist = playlistRepository.findById(playlistId)
				.orElseThrow(() -> new PlaylistNotFoundException("Playlist not found"));
		
		Song song = songRepository.findById(songId)
				.orElseThrow(() -> new SongNotFoundException("Song not found"));
		
		playlist.getSongs().remove(song);		
		return playlistRepository.save(playlist);
	}
	
	public List<Playlist> getUserPlaylists(String username){
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
		
		return playlistRepository.findByUser(user);
	}
	
	public Optional<Playlist> getPlaylistById(Long playlistId){
		return playlistRepository.findById(playlistId);
	}
	
	public List<Song> shufflePlaylist(Playlist playlist) {
		List<Song> songs = playlist.getSongs();
		Collections.shuffle(songs); // Shuffles the songs in the playlist
		return songs;
	}
}
