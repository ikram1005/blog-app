package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Dto.UserDto;
import com.blog.entities.ApiResponse;
import com.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto userDto2=userService.createUser(userDto);
		return new ResponseEntity<UserDto>(userDto2,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uId){
		UserDto userDto2=userService.updateUser(userDto, uId);
		
		return new ResponseEntity<UserDto>(userDto2,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
		UserDto userDto=userService.getUserById(userId);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> userDtos=userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(userDtos,HttpStatus.OK);
	}

}
