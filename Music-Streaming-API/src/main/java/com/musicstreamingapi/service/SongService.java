package com.musicstreamingapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicstreamingapi.model.Song;
import com.musicstreamingapi.repository.SongRepository;

@Service
public class SongService {
	
	@Autowired
	private SongRepository songRepository;
	
	public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
	
	public List<Song> searchByTitle(String title){
		return songRepository.findByTitleContainingIgnoreCase(title);
	}
	
	public List<Song> searchByArtist(String artist){
		return songRepository.findByArtistContainingIgnoreCase(artist);
	}
	
	public List<Song> searchByAlbum(String album){
		return songRepository.findByAlbumContainingIgnoreCase(album);
	}
	
	public List<Song> searchByGenre(String genre){
		return songRepository.findByGenreContainingIgnoreCase(genre);
	}
	
	public Optional<Song> getSongById(Long id){
		return songRepository.findById(id);
	}
}
