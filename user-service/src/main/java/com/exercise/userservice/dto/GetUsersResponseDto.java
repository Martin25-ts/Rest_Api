package com.exercise.userservice.dto;

import java.util.List;

public class GetUsersResponseDto {
	
	private Boolean result;
	private List<UserDto> users;
	
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public List<UserDto> getUsers() {
		return users;
	}
	public void setUsers(List<UserDto> users) {
		this.users = users;
	}
	
	
	
}
