package com.musicstreamingapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicstreamingapi.model.Playlist;
import com.musicstreamingapi.model.User;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
	List<Playlist> findByUser(User user);
}
