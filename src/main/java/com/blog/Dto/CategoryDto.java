package com.blog.Dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;

	@NotEmpty
	private String title;

	@NotEmpty
	private String description;
}
