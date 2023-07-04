package com.blog.implementaions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Dto.CategoryDto;
import com.blog.entities.Category;
import com.blog.exceptions.UserException;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category category2 = categoryRepo.save(category);
		return modelMapper.map(category2, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new UserException("Category", "id", categoryId));
		cat.setTitle(categoryDto.getTitle());
		cat.setDescription(categoryDto.getDescription());
		Category updateCategory = categoryRepo.save(cat);

		return modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new UserException("Category", "id", categoryId));
		
		categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new UserException("Category", "id", categoryId));
		return modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories=categoryRepo.findAll();
		List<CategoryDto> categoryDtos = categories.stream().map((cate)->modelMapper.map(cate, CategoryDto.class)).collect(Collectors.toList());
	
		return categoryDtos;
	}

}
