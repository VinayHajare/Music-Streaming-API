package com.musicstreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicstreamingapi.model.PlaybackHistory;
import com.musicstreamingapi.model.User;
import java.util.List;


public interface PlaybackHistoryRepository extends JpaRepository<PlaybackHistory, Long> {
	List<PlaybackHistory> findByUser(User user);
}
