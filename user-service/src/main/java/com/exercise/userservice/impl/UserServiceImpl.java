package com.exercise.userservice.impl;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exercise.userservice.dto.CreateUserRequestDto;
import com.exercise.userservice.dto.CreateUserResponseDto;
import com.exercise.userservice.dto.GetUserByIdRequestDto;
import com.exercise.userservice.dto.GetUserByIdResponseDto;
import com.exercise.userservice.dto.GetUsersRequestDto;
import com.exercise.userservice.dto.GetUsersResponseDto;
import com.exercise.userservice.dto.UserDto;
import com.exercise.userservice.entity.User;
import com.exercise.userservice.repository.UserRepository;
import com.exercise.userservice.service.UserService;
import java.time.Instant;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository ;
	
	private UserDto convertListingToListingDto(User user) {
	    UserDto userDto = new UserDto();
	    BeanUtils.copyProperties(user, userDto);

	    return userDto;
	  }
	private Long nowInEpochMicroSecond() {
	    return ChronoUnit.MICROS.between(Instant.EPOCH, Instant.now());
	}
	
	@Override
	public GetUsersResponseDto getUsers(GetUsersRequestDto requestDto) {
		
		PageRequest pageRequest = PageRequest.of(requestDto.getPageNum(), requestDto.getPageSize(),Sort.by("createdAt").descending());
		
		
		List<User> result =  userRepository.findAll();
		List<UserDto> userDtoList = result.stream().map(this::convertListingToListingDto).collect(Collectors.toList());
		GetUsersResponseDto responseDto = new GetUsersResponseDto();
		
		responseDto.setResult(true);
		responseDto.setUsers(userDtoList);
		
		return responseDto;
	}


	@Override
	public GetUserByIdResponseDto getUserById(GetUserByIdRequestDto requestDto) {
		User user = userRepository.findById(requestDto.getId()).orElse(new User());
		
		GetUserByIdResponseDto responseDto = new GetUserByIdResponseDto();
		
		responseDto.setResult(true);
		responseDto.setUser(convertListingToListingDto(user));
		
		return responseDto;
	}

	@Override
	public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
		User user = new User();
		
		user.setName(requestDto.getName());
		final long timestampInMicroSecond = nowInEpochMicroSecond();
		
		user.setCreatedAt(timestampInMicroSecond);
		user.setUpdatedAt(timestampInMicroSecond);
		
		userRepository.save(user);
		CreateUserResponseDto responseDto = new CreateUserResponseDto();
		responseDto.setResutl(true);
		responseDto.setUser(convertListingToListingDto(user));
		
		return null;
	}
}
