package com.exercise.userservice.dto;

public class GetUserByIdResponseDto {
	private Boolean result;
	private UserDto user;
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
}
