package com.musicstreamingapi.dto;

public class PlaybackHistoryDTO {
	
	private Long id;
	private Long songId;
	private String songTitle;
	private String artistName;
	private String playbackDate;
	
	public PlaybackHistoryDTO(Long id, Long songId, String songTitle, String artistName, String playbackDate) {
		this.id = id;
		this.songId = songId;
		this.songTitle = songTitle;
		this.artistName = artistName;
		this.playbackDate = playbackDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSongId() {
		return songId;
	}
	
	public void setSongId(Long songId) {
		this.songId = songId;
	}
	
	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getPlaybackDate() {
		return playbackDate;
	}

	public void setPlaybackDate(String playbackDate) {
		this.playbackDate = playbackDate;
	}
	
	
}
