package com.blog.services;

import com.blog.Dto.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);

	void delete(Integer commentId);

}
