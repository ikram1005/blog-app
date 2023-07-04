package com.blog.controllers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
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

import com.blog.Dto.CategoryDto;
import com.blog.entities.ApiResponse;
import com.blog.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto categoryDto2=categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
		CategoryDto categoryDto2=categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/getCategory/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
		CategoryDto categoryDto2=categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.OK);
	}
	
	@GetMapping("/getCategory")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> categoryDto2=categoryService.getAllCategory();
		return ResponseEntity.ok(categoryDto2);
	}
	
}


