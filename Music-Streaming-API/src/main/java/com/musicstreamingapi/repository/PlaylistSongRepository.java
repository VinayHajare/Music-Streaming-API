package com.musicstreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicstreamingapi.model.PlaylistSong;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long>{

}
