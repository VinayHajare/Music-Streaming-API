package com.musicstreamingapi.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicstreamingapi.model.PlaybackHistory;
import com.musicstreamingapi.model.Song;
import com.musicstreamingapi.model.User;
import com.musicstreamingapi.repository.PlaybackHistoryRepository;

@Service
public class RecommendationService {
	
	@Autowired
	private PlaybackHistoryRepository playbackHistoryRepository;
	
	public List<Song> getRecommendations(User user) {
		List<PlaybackHistory> histories = playbackHistoryRepository.findByUser(user);
		
		// Recommend the most frequently played 10 songs
		Map<Song, Long> songFrequency = histories.stream()
				.collect(Collectors.groupingBy(PlaybackHistory::getSong, Collectors.counting()));
		
		return songFrequency.entrySet().stream()
				.sorted(Map.Entry.<Song, Long> comparingByValue().reversed())
				.limit(10)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
}
