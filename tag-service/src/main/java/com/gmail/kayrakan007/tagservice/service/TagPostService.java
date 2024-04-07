package com.gmail.kayrakan007.tagservice.service;

import java.util.List;

public interface TagPostService {
	
	void addTagsToPost(Long postId, List<Long> tagIds);
	
	void removeTagsFromPost(Long postId, List<Long> tagIds);

}