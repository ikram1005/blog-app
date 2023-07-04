package com.blog.implementaions;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.Dto.PostDto;
import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.PostResponse;
import com.blog.entities.User;
import com.blog.exceptions.UserException;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("user", "id", userId));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new UserException("category", "id", categoryId));

		Post post = modelMapper.map(postDto, Post.class);
		post.setImage("default.png");
		post.setPost_date(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post createPost = postRepo.save(post);
		return modelMapper.map(createPost, PostDto.class);

	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new UserException("post", "id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImage(postDto.getImage());
		
		Post save = postRepo.save(post);
		return modelMapper.map(save,PostDto.class);
		
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new UserException("post", "id", postId));
		postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer PageSize,String sortBy,String sortDir) {
		
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNumber, PageSize,sort);
		
		Page<Post> pages = postRepo.findAll(pageable);
		List<Post> posts=pages.getContent();
		
		List<PostDto> collect = posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(collect);
		postResponse.setPageNumber(pages.getNumber());
		postResponse.setPageSize(pages.getSize());
		postResponse.setTotalElements(pages.getTotalElements());
		postResponse.setTotalPages(pages.getTotalPages());;
		postResponse.setLastPage(pages.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new UserException("post", "id", postId));
		
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByGategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new UserException("category", "id", categoryId));

		List<Post> catgories = postRepo.findByCategory(category);

		return catgories.stream().map((cat) -> modelMapper.map(cat, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("user", "id", userId));
		List<Post> catgories = postRepo.findByUser(user);

		return catgories.stream().map((cat) -> modelMapper.map(cat, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		return posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

}
