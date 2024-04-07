package com.gmail.kayrakan007.tagservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.kayrakan007.tagservice.model.request.TagRequest;
import com.gmail.kayrakan007.tagservice.model.response.TagResponse;
import com.gmail.kayrakan007.tagservice.service.TagService;

@RestController
public class TagController {
	
	TagService tagService;

	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	@GetMapping
	public List<TagResponse> getAllTags() {
		
		return tagService.getAllTags();
	}
	
	@GetMapping("/{id}")
	public TagResponse getTagById(@PathVariable("id") Long id) {
		
		return tagService.getTagById(id);
	}
	
	@PostMapping()
	public TagResponse createTag(@RequestBody TagRequest tagRequest) {
		
		return tagService.createTag(tagRequest);
	}
	
	@PutMapping("/{id}")
	public TagResponse updateTag(@PathVariable("id") Long id, @RequestBody TagRequest tagRequest) {
		
		return tagService.updateTag(id, tagRequest);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTag(@PathVariable Long id) {
		
		tagService.deleteTag(id);
	}

}
