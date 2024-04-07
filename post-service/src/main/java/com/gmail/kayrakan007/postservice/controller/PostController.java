package com.gmail.kayrakan007.postservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.kayrakan007.postservice.model.request.PostRequest;
import com.gmail.kayrakan007.postservice.model.response.PostResponse;
import com.gmail.kayrakan007.postservice.service.PostService;

@RestController
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping({ "/new", "/new/{parentId}" })
	public List<PostResponse> getNewPostsByParentId(
			@PathVariable(name = "parentId", required = false) Long parentId,
			@RequestParam("pageNo") Integer pageNo,
			@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

		return postService.getNewPostsByParentId(parentId, pageNo, pageSize);
	}

	@GetMapping({ "/top", "/top/{parentId}" })
	public List<PostResponse> getTopPostsByParentIdAndTime(
			@PathVariable(name = "parentId", required = false) Long parentId,
			@RequestParam(name = "time", required = false, defaultValue = "all") String time,
			@RequestParam("pageNo") Integer pageNo,
			@RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

		return postService.getTopPostsByParentIdAndTime(parentId, time, pageNo, pageSize);
	}

	@GetMapping("/{id}")
	public PostResponse getPostById(@PathVariable("id") Long id) {

		return postService.getPostById(id);
	}

	@PostMapping()
	public PostResponse createPost(@RequestBody PostRequest postRequest) {

		return postService.createPost(postRequest);
	}

	@PutMapping("/{id}")
	public PostResponse updatePost(@PathVariable("id") Long id, @RequestBody PostRequest postRequest) {

		return postService.updatePost(id, postRequest);
	}

	@DeleteMapping("/{id}")
	public void deletePost(@PathVariable("id") Long id) {

		postService.deletePost(id);
	}

	@GetMapping("/exists/{id}")
	public boolean existsById(@PathVariable("id") Long id) {

		return postService.existsById(id);
	}

}
