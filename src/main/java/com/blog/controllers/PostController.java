package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Dto.PostDto;
import com.blog.config.AppConstant;
import com.blog.entities.ApiResponse;
import com.blog.entities.PostResponse;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/create/{userId}/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	@GetMapping("/getAllPosts")
	public ResponseEntity<PostResponse> getAllPost
	(@RequestParam(value = "pageNumber",defaultValue =AppConstant.PAGENUMBER,required = false)Integer pageNumber,
	@RequestParam(value = "pageSize",defaultValue =AppConstant.PAGESIZE,required = false)Integer pageSize,
	@RequestParam(value = "sortBy",defaultValue =AppConstant.SORTBY,required = false)String sortBy,
	@RequestParam(value = "sortDir",defaultValue =AppConstant.SORTDIR,required = false)String SortDir
			){
		PostResponse posts = postService.getAllPost(pageNumber, pageSize, sortBy, SortDir);
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
	}

	@GetMapping("/getPost/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) {
		PostDto postDto = postService.getPostById(postId);

		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto save = postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(save, HttpStatus.OK);
	}

	@GetMapping("/delete/{postId}")
	public ResponseEntity<ApiResponse> delete(@PathVariable Integer postId) {
		postService.deletePost(postId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/getPostByCat/{catId}")
	public ResponseEntity<List<PostDto>> getPostByCat(@PathVariable Integer catId) {
		List<PostDto> postByGategory = postService.getPostByGategory(catId);

		return new ResponseEntity<List<PostDto>>(postByGategory, HttpStatus.OK);
	}

	@GetMapping("/getPostByUser/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> postByUser = postService.getPostByUser(userId);

		return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
	}
	
	@GetMapping("/searchPost/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword) {
		List<PostDto> post =postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
	}
}
