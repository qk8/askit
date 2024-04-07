package com.gmail.kayrakan007.postservice.service.implementation;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.kayrakan007.commons.exception.PostNotFoundException;
import com.gmail.kayrakan007.commons.model.event.VoteEvent;
import com.gmail.kayrakan007.postservice.entity.Post;
import com.gmail.kayrakan007.postservice.mapper.PostMapper;
import com.gmail.kayrakan007.postservice.model.request.PostRequest;
import com.gmail.kayrakan007.postservice.model.response.PostResponse;
import com.gmail.kayrakan007.postservice.repository.PostRepository;
import com.gmail.kayrakan007.postservice.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final PostMapper postMapper;

	public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
		this.postRepository = postRepository;
		this.postMapper = postMapper;
	}

	@Override
	public List<PostResponse> getNewPostsByParentId(Long parentId, Integer pageNo, Integer pageSize) {

		Pageable pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());

		List<Post> posts = postRepository.findByParentId(parentId, pageRequest);

		List<PostResponse> postResponses = posts.stream().map(post -> postMapper.postToPostResponse(post))
				.collect(Collectors.toList());

		return postResponses;
	}

	@Override
	public List<PostResponse> getTopPostsByParentIdAndTime(Long parentId, String time, Integer pageNo,
			Integer pageSize) {

		Sort sort = Sort.by("totalVotes").descending().and(Sort.by("createdAt").descending());
		Pageable pageRequest = PageRequest.of(pageNo, pageSize, sort);

		Instant since = Instant.EPOCH;

		if (time == "day") {
			since = Instant.now().minus(Duration.ofDays(1));
		} else if (time == "week") {
			since = Instant.now().minus(Duration.ofDays(7));
		} else if (time == "month") {
			since = Instant.now().minus(Duration.ofDays(7));
		} else if (time == "year") {
			since = Instant.now().minus(Duration.ofDays(365));
		} else if (time == "all") {
			since = Instant.EPOCH;
		}

		List<Post> posts = postRepository.findByParentIdAndCreatedAtGreaterThan(parentId, since, pageRequest);

		List<PostResponse> postResponses = posts.stream().map(post -> postMapper.postToPostResponse(post))
				.collect(Collectors.toList());

		return postResponses;

	}

	@Override
	public PostResponse getPostById(Long id) {

		Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		PostResponse postResponse = postMapper.postToPostResponse(post);

		return postResponse;
	}

	@Override
	public PostResponse createPost(PostRequest postRequest) {

		Post post = postMapper.postRequestToPost(postRequest);
		Post createdPost = postRepository.save(post);
		PostResponse createdPostResponse = postMapper.postToPostResponse(createdPost);

		return createdPostResponse;
	}

	@Override
	public PostResponse updatePost(Long id, PostRequest postRequest) {

		Post postFromDb = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		postFromDb.setTitle(postRequest.title());
		postFromDb.setText(postRequest.text());
		Post updatedPost = postRepository.save(postFromDb);
		PostResponse updatedPostResponse = postMapper.postToPostResponse(updatedPost);

		return updatedPostResponse;
	}

	@Transactional
	@Override
	public void updatePost(VoteEvent voteEvent) {

		boolean postExists = postRepository.existsById(voteEvent.postId());

		if (!postExists) {
			throw new PostNotFoundException();
		}

		Integer change = 0;

		if (voteEvent.action().equals("created") && voteEvent.voteTypeName().equals("upvote")) {
			change = +1;

		} else if (voteEvent.action().equals("deleted") && voteEvent.voteTypeName().equals("downvote")) {
			change = +1;

		} else if (voteEvent.action().equals("created") && voteEvent.voteTypeName().equals("downvote")) {
			change = -1;

		} else if (voteEvent.action().equals("deleted") && voteEvent.voteTypeName().equals("upvote")) {
			change = -1;

		}

		if (change == +1) {
			postRepository.increaseTotalVotes(voteEvent.postId());
		} else if (change == -1) {
			postRepository.decreaseTotalVotes(voteEvent.postId());
		}

	}

	@Override
	public void deletePost(Long id) {

		Post postFromDb = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
		postRepository.delete(postFromDb);
	}

	@Override
	public boolean existsById(Long id) {

		return postRepository.existsById(id);
	}

}
