package com.blog.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(max  = 10,message = "Maximum length of name is 10 characters")
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	@Size(max = 10)
	private String password;
	
	@NotEmpty
	private String about;
}
