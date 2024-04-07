package com.gmail.kayrakan007.tagservice.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.kayrakan007.commons.exception.PostNotFoundException;
import com.gmail.kayrakan007.tagservice.client.PostClient;
import com.gmail.kayrakan007.tagservice.entity.Tag;
import com.gmail.kayrakan007.tagservice.entity.TagPost;
import com.gmail.kayrakan007.tagservice.exception.TagNotFoundException;
import com.gmail.kayrakan007.tagservice.repository.TagPostRepository;
import com.gmail.kayrakan007.tagservice.repository.TagRepository;
import com.gmail.kayrakan007.tagservice.service.TagPostService;

@Service
public class TagPostServiceImpl implements TagPostService {

	TagPostRepository tagPostRepository;
	TagRepository tagRepository;
	PostClient postClient;

	public TagPostServiceImpl(TagPostRepository tagPostRepository, TagRepository tagRepository, PostClient postClient) {
		this.tagPostRepository = tagPostRepository;
		this.tagRepository = tagRepository;
		this.postClient = postClient;
	}

	@Transactional
	@Override
	public void addTagsToPost(Long postId, List<Long> tagIds) {

		boolean postExists = postClient.existsById(postId);

		if (!postExists) {
			throw new PostNotFoundException();
		}

		for (Long tagId : tagIds) {

			Optional<Tag> tag = tagRepository.findById(tagId);

			if (tag.isEmpty()) {
				throw new TagNotFoundException();
			}

			Optional<TagPost> tagPost = tagPostRepository.findFirstByTagIdAndPostId(tagId, postId);

			if (tagPost.isEmpty()) {

				TagPost newTagPost = new TagPost();
				newTagPost.setTagId(tagId);
				newTagPost.setPostId(postId);
				tagPostRepository.save(newTagPost);
				tagRepository.increaseQuantity(tagId);

			}

		}

	}

	@Transactional
	@Override
	public void removeTagsFromPost(Long postId, List<Long> tagIds) {

		for (Long tagId : tagIds) {
			Optional<TagPost> tagPost = tagPostRepository.findFirstByTagIdAndPostId(tagId, postId);

			if (tagPost.isPresent()) {
				tagPostRepository.delete(tagPost.get());
				tagRepository.decreaseQuantity(tagId);
			}

		}
	}

}
