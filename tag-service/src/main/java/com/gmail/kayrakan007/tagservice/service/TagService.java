package com.gmail.kayrakan007.tagservice.service;

import java.util.List;

import com.gmail.kayrakan007.tagservice.model.request.TagRequest;
import com.gmail.kayrakan007.tagservice.model.response.TagResponse;

public interface TagService {

	List<TagResponse> getAllTags();

	TagResponse getTagById(Long id);

	TagResponse createTag(TagRequest tagRequest);

	TagResponse updateTag(Long id, TagRequest tagRequest);

	void deleteTag(Long id);

}
