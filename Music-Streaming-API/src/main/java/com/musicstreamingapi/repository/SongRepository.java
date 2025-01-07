package com.musicstreamingapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicstreamingapi.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
	List<Song> findByTitleContainingIgnoreCase(String title);
	List<Song> findByArtistContainingIgnoreCase(String artist);
	List<Song> findByAlbumContainingIgnoreCase(String album);
	List<Song> findByGenreContainingIgnoreCase(String genre);
}
