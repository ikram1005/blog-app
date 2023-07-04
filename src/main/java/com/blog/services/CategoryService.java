package com.blog.services;

import java.util.List;

import com.blog.Dto.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	void deleteCategory(Integer categoryId);

	CategoryDto getCategory(Integer categoryId);

	List<CategoryDto> getAllCategory();
}
