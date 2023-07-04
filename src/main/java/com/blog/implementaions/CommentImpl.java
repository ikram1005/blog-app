package com.blog.implementaions;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Dto.CommentDto;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.UserException;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.CommentService;

@Service
public class CommentImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new UserException("post", "id", postId));

		Comment comment = modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);

		Comment save = commentRepo.save(comment);

		return modelMapper.map(save, CommentDto.class);

	}

	@Override
	public void delete(Integer commentId) {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new UserException("comment", "id", commentId));

		commentRepo.delete(comment);
	}

}
