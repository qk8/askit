package com.gmail.kayrakan007.tagservice.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisTemplate;
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

	private final TagRepository tagRepository;
	private final TagMapper tagMapper;
	private final RedisTemplate<String, TagResponse> redisTemplate;

	public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper,
			RedisTemplate<String, TagResponse> redisTemplate) {
		this.tagRepository = tagRepository;
		this.tagMapper = tagMapper;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public List<TagResponse> getAllTags() {

		if (redisTemplate.hasKey("tagResponse"))
			return redisTemplate.opsForList().range("tagResponse", 0, -1);

		List<Tag> allTags = tagRepository.findAll();
		List<TagResponse> allTagResponses = allTags.stream().map(tag -> tagMapper.tagToTagResponse(tag))
				.collect(Collectors.toList());

		if (!allTagResponses.isEmpty())
			redisTemplate.opsForList().rightPushAll("tagResponse", allTagResponses);

		return allTagResponses;
	}

	@Override
	public TagResponse getTagById(Long id) {

		if (redisTemplate.hasKey("tagResponse:" + id.toString()))
			return redisTemplate.opsForValue().get("tagResponse:" + id.toString());

		Tag tag = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
		TagResponse tagResponse = tagMapper.tagToTagResponse(tag);

		redisTemplate.opsForValue().set("tagResponse:" + id.toString(), tagResponse);

		return tagResponse;
	}

	@Override
	public TagResponse createTag(TagRequest tagRequest) {

		Tag tag = tagMapper.tagRequestToTag(tagRequest);
		Tag createdTag = tagRepository.save(tag);
		TagResponse createdTagResponse = tagMapper.tagToTagResponse(createdTag);

		redisTemplate.delete("tagResponse");

		return createdTagResponse;
	}

	@Override
	public TagResponse updateTag(Long id, TagRequest tagRequest) {

		Tag tagFromDb = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
		tagFromDb.setTagName(tagRequest.tagName());
		Tag updatedTag = tagRepository.save(tagFromDb);
		TagResponse tagResponse = tagMapper.tagToTagResponse(updatedTag);

		redisTemplate.delete("tagResponse");
		redisTemplate.delete("tagResponse:" + id.toString());

		return tagResponse;
	}

	@Override
	public void deleteTag(Long id) {

		Tag tag = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
		tagRepository.delete(tag);

		redisTemplate.delete("tagResponse");
		redisTemplate.delete("tagResponse:" + id.toString());

	}

}
