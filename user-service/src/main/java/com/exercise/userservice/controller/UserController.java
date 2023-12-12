package com.exercise.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.userservice.dto.CreateUserRequestDto;
import com.exercise.userservice.dto.CreateUserResponseDto;
import com.exercise.userservice.dto.GetUserByIdRequestDto;
import com.exercise.userservice.dto.GetUserByIdResponseDto;
import com.exercise.userservice.dto.GetUsersRequestDto;
import com.exercise.userservice.dto.GetUsersResponseDto;
import com.exercise.userservice.impl.UserServiceImpl;

@Validated
@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl userservice;
	
	
	@GetMapping
	public GetUsersResponseDto getUsers(
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
			
	) {
		GetUsersRequestDto requestDto = new GetUsersRequestDto();
		requestDto.setPageNum(pageNum);
		requestDto.setPageSize(pageSize);
		
		return userservice.getUsers(requestDto);
	}
	
	@PostMapping
	public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
		return userservice.createUser(requestDto);
	}
	
	
	@GetMapping("/{id}")
	public GetUserByIdResponseDto getUser(
				@PathVariable(name = "id") Integer id
			) {
		
		GetUserByIdRequestDto requestDto = new GetUserByIdRequestDto();
		requestDto.setId(id);
		
		return userservice.getUserById(requestDto);
	}
	
}
