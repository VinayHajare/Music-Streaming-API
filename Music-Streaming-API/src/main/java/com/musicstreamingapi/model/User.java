package com.musicstreamingapi.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String email;
	private String password;
	private String bio;
	private String profilePicture;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles;
	
	@ManyToMany
	@JoinTable(
			name = "user_follows_artists",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "artist_id")
	)
	private Set<Artist> followedArtists = new HashSet<>(); // User can follow multiple artists

	public User() {}
	
	public User(String username, String email, String password, String bio, String profilePicture, Set<String> roles) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.bio = bio;
		this.profilePicture = profilePicture;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getProfilePicture() {
		return this.profilePicture;
	}
	
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Set<Artist> getFollowedArtists() {
		return followedArtists;
	}

	public void setFollowedArtists(Set<Artist> followedArtists) {
		this.followedArtists = followedArtists;
	}
	
}
