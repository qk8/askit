package com.gmail.kayrakan007.tagservice.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gmail.kayrakan007.tagservice.entity.Tag;
import com.gmail.kayrakan007.tagservice.exception.TagNotFoundException;
import com.gmail.kayrakan007.tagservice.mapper.TagMapper;
import com.gmail.kayrakan007.tagservice.model.request.TagRequest;
import com.gmail.kayrakan007.tagservice.model.response.TagResponse;
import com.gmail.kayrakan007.tagservice.repository.TagRepository;
import com.gmail.kayrakan007.tagservice.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	TagRepository tagRepository;
	TagMapper tagMapper;

	public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
		this.tagRepository = tagRepository;
		this.tagMapper = tagMapper;
	}

	@Override
	public List<TagResponse> getAllTags() {

		List<Tag> tags = tagRepository.findAll();
		List<TagResponse> tagResponses = tags.stream().map(tag -> tagMapper.tagToTagResponse(tag))
				.collect(Collectors.toList());
		
		return tagResponses;
	}

	@Override
	public TagResponse getTagById(Long id) {
		
		Tag tag = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
		TagResponse tagResponse = tagMapper.tagToTagResponse(tag);
		
		return tagResponse;
	}

	@Override
	public TagResponse createTag(TagRequest tagRequest) {
		
		Tag tag = tagMapper.tagRequestToTag(tagRequest);
		Tag createdTag = tagRepository.save(tag);
		TagResponse createdTagResponse = tagMapper.tagToTagResponse(createdTag);
		
		return createdTagResponse;
	}

	@Override
	public TagResponse updateTag(Long id, TagRequest tagRequest) {
		
		Tag tagFromDb = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
		tagFromDb.setTagName(tagRequest.tagName());
		Tag updatedTag = tagRepository.save(tagFromDb);
		TagResponse tagResponse = tagMapper.tagToTagResponse(updatedTag);
		
		return tagResponse;
	}

	@Override
	public void deleteTag(Long id) {
		
		Tag tag = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
		tagRepository.delete(tag);

	}

}
