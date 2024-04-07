package com.gmail.kayrakan007.postservice.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.gmail.kayrakan007.postservice.entity.Post;
import com.gmail.kayrakan007.postservice.model.request.PostRequest;
import com.gmail.kayrakan007.postservice.model.response.PostResponse;
import com.gmail.kayrakan007.postservice.repository.PostRepository;

@Component
public class PostMapper {

	private final PostRepository postRepository;

	public PostMapper(PostRepository postRepository) {

		this.postRepository = postRepository;
	}

	public Post postRequestToPost(PostRequest postRequest) {

		Post post = new Post();
		post.setUserId(postRequest.userId());
		
		if (postRequest.parentId() != null) {
			Optional<Post> postParent = postRepository.findById(postRequest.parentId());
			post.setParent(postParent.orElse(null));
		}

		post.setTitle(postRequest.title());
		post.setText(postRequest.text());

		return post;
	}

	public PostResponse postToPostResponse(Post post) {

		Post parent = post.getParent();
		Long parentId = null;

		if (parent != null) {
			parentId = parent.getId();
		}

		PostResponse postResponse = new PostResponse(
				post.getId(),
				post.getUserId(),
				parentId,
				post.getTitle(),
				post.getText(),
				post.getCreatedAt(),
				post.getModifiedAt(),
				post.getTotalVotes());

		return postResponse;
	}

}
