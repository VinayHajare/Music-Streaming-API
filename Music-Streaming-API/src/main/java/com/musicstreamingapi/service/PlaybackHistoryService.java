package com.musicstreamingapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.musicstreamingapi.dto.PlaybackHistoryDTO;
import com.musicstreamingapi.model.PlaybackHistory;
import com.musicstreamingapi.model.Song;
import com.musicstreamingapi.model.User;
import com.musicstreamingapi.repository.PlaybackHistoryRepository;

@Service
public class PlaybackHistoryService {

	 private final PlaybackHistoryRepository playbackHistoryRepository;
	 
	 public PlaybackHistoryService(PlaybackHistoryRepository playbackHistoryRepository) {
		this.playbackHistoryRepository = playbackHistoryRepository;
	 }
	 
	 public void logPlayback(User user, Song song) {
		 PlaybackHistory history = new PlaybackHistory();
		 history.setUser(user);
		 history.setSong(song);
		 playbackHistoryRepository.save(history);
	 }
	 
	 public List<PlaybackHistoryDTO> getPlaybackHistory(User user) {
		List<PlaybackHistory> playbackHistories = playbackHistoryRepository.findByUser(user);
		
		return playbackHistories.stream().map(history ->
				new PlaybackHistoryDTO(
						history.getId(),
						history.getSong().getId(),
						history.getSong().getTitle(),
						history.getSong().getArtist(),
						history.getPlayedAt().toString()
				)
		).collect(Collectors.toList());
	}
	 
}
