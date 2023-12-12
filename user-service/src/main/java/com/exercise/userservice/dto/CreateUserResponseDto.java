package com.exercise.userservice.dto;

public class CreateUserResponseDto {
	private Boolean resutl;
	private UserDto user;
	
	public Boolean getResutl() {
		return resutl;
	}
	public void setResutl(Boolean resutl) {
		this.resutl = resutl;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
}
