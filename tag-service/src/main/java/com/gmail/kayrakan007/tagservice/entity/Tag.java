package com.gmail.kayrakan007.tagservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tag_name", nullable = false, unique = true)
	private String tagName;

	@Column(name = "post_quantity", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
	private Long postQuantity = 0L;

	public Tag() {
	}
	
	

	public Tag(Long id, String tagName, Long postQuantity) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.postQuantity = postQuantity;
	}



	public Long getId() {
		return id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Long getPostQuantity() {
		return postQuantity;
	}

}
