package com.gmail.kayrakan007.tagservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.kayrakan007.tagservice.service.TagPostService;

@RestController
@RequestMapping("/tagpost")
public class TagPostController {

	TagPostService tagPostService;

	public TagPostController(TagPostService tagPostService) {
		this.tagPostService = tagPostService;
	}
	
	@PostMapping("/{postId}")
	public void addTagsToPost(@PathVariable("postId") Long id, @RequestBody List<Long> tagIds) {
		
		tagPostService.addTagsToPost(id, tagIds);
	}
	
	@DeleteMapping("/{postId}")
	public void removeTagsFromPost(@PathVariable("postId") Long postId, @RequestBody List<Long> tagIds) {
		
		tagPostService.removeTagsFromPost(postId, tagIds);
	}
}
