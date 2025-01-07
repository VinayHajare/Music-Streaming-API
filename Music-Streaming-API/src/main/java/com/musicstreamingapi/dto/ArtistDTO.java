package com.musicstreamingapi.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ArtistDTO {
	private Long id;
	private String name;
	private String genre;
	private List<Map<String, Object>> followers;
	
	public ArtistDTO(Long id, String name, String genre, List<Map<String, Object>> list) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.followers = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<Map<String, Object>> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Map<String, Object>> followers) {
		this.followers = followers;
	}

}
