package com.gmail.kayrakan007.tagservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tag_post", uniqueConstraints = @UniqueConstraint(columnNames = {"tag_id", "post_id"}))
public class TagPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tag_id", nullable = false)
	private Long tagId;
	
	@Column(name = "post_id", nullable = false)
	private Long postId;

	public TagPost() {
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getId() {
		return id;
	}
	
	
}
