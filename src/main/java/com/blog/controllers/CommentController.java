package com.blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Dto.CommentDto;
import com.blog.entities.ApiResponse;
import com.blog.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create/{postId}")
	public ResponseEntity<CommentDto> create(@Valid @RequestBody CommentDto comment,@PathVariable Integer postId){
		
		CommentDto commentDto=commentService.createComment(comment, postId);
		
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer commentId){
		commentService.delete(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully",true),HttpStatus.OK);
	}
	
}
