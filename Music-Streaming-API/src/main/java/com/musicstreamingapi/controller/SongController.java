package com.musicstreamingapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musicstreamingapi.model.Song;
import com.musicstreamingapi.service.SongService;


@RestController
@RequestMapping("api/songs")
public class SongController {
	
	@Autowired
	private SongService songService;
	
	@GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }
	
	@GetMapping("/search")
	public ResponseEntity<List<Song>> searchSong(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "artist", required = false) String artist,
			@RequestParam(value = "album", required = false) String album,
			@RequestParam(value = "genre", required = false) String genre) {
		if(title != null) {
			return ResponseEntity.ok(songService.searchByTitle(title));
		}else if(artist != null){
			return ResponseEntity.ok(songService.searchByArtist(artist));
		}else if(album != null) {
			return ResponseEntity.ok(songService.searchByAlbum(album));
		}else if(genre != null) {
			return ResponseEntity.ok(songService.searchByGenre(genre));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Song> getMethodName(@PathVariable Long id) {
		Optional<Song> song = songService.getSongById(id);
		return song.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
}
