package com.gmail.kayrakan007.postservice.entity;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Post parent;

	@OneToMany(mappedBy = "parent", orphanRemoval = true)
	private List<Post> children;

	@Column(name = "title")
	private String title;

	@Column(name = "text", nullable = false)
	private String text;

	@CreationTimestamp
	@Column(name = "created_at")
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "modified_at")
	private Instant modifiedAt;

	@Column(name = "total_votes", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
	private Integer totalVotes = 0;

	public Post() {
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Post getParent() {
		return parent;
	}

	public void setParent(Post parent) {
		this.parent = parent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getModifiedAt() {
		return modifiedAt;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Integer totalVotes) {
		this.totalVotes = totalVotes;
	}

}
