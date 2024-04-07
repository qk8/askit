package com.gmail.kayrakan007.postservice.service;

import java.util.List;

import com.gmail.kayrakan007.commons.model.event.VoteEvent;
import com.gmail.kayrakan007.postservice.model.request.PostRequest;
import com.gmail.kayrakan007.postservice.model.response.PostResponse;

public interface PostService {
	
	List<PostResponse> getNewPostsByParentId(Long parentId, Integer pageNo, Integer pageSize);

	List<PostResponse> getTopPostsByParentIdAndTime(Long parentId, String time, Integer pageNo, Integer pageSize);
	
	PostResponse getPostById(Long id);
	
	PostResponse createPost(PostRequest postRequest);
	
	PostResponse updatePost(Long id, PostRequest postRequest);

	void updatePost(VoteEvent voteEvent);
	
	void deletePost(Long id);
	
	boolean existsById(Long id);

}
