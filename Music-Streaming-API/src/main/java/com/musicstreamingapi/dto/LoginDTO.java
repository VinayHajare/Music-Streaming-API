package com.musicstreamingapi.dto;

import lombok.Data;

@Data
public class LoginDTO {
	
	private String token;
	private UserDTO user;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}	
}
