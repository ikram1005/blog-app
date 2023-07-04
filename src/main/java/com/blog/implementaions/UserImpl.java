package com.blog.implementaions;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Dto.UserDto;
import com.blog.entities.User;
import com.blog.exceptions.UserException;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User saveUser = userRepo.save(user);
		return userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("user", "id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = userRepo.save(user);
		UserDto userDto2 = this.userToDto(updatedUser);
		return userDto2;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("user", "id", userId));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();

		List<UserDto> userDtos = users.stream().map(u -> userToDto(u)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("user", "id", userId));
		userRepo.delete(user);
	}

	public User dtoToUser(UserDto userDto) {
		User user = modelMapper.map(userDto,User.class);
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto =modelMapper.map(user,UserDto.class);
		return userDto;
	}

}
