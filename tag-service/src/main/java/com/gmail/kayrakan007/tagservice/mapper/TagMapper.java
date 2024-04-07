package com.gmail.kayrakan007.tagservice.mapper;

import org.springframework.stereotype.Component;

import com.gmail.kayrakan007.tagservice.entity.Tag;
import com.gmail.kayrakan007.tagservice.model.request.TagRequest;
import com.gmail.kayrakan007.tagservice.model.response.TagResponse;
import com.gmail.kayrakan007.tagservice.repository.TagRepository;

@Component
public class TagMapper {
	
	TagRepository tagRepository;

	public TagMapper(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	public Tag tagRequestToTag(TagRequest tagRequest) {
		
		Tag tag = new Tag();
		tag.setTagName(tagRequest.tagName());
		
		return tag;
		
	}
	
	public TagResponse tagToTagResponse(Tag tag) {
		
		TagResponse tagResponse = new TagResponse(
				tag.getId(), 
				tag.getTagName(), 
				tag.getPostQuantity()
				);
		
		return tagResponse;
		
		
	}
}
