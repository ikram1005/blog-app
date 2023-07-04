package com.blog.services;

import java.util.List;

import com.blog.Dto.PostDto;
import com.blog.entities.PostResponse;
public interface PostService {
	 
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse  getAllPost(Integer pageNumber,Integer PageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostByGategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPost(String keyword);
	
	

}
