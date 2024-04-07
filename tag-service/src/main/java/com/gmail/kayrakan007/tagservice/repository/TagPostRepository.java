package com.gmail.kayrakan007.tagservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.kayrakan007.tagservice.entity.TagPost;

@Repository
public interface TagPostRepository extends JpaRepository<TagPost, Long>{
	
	Optional<TagPost> findFirstByTagIdAndPostId(Long tagId, Long postId);

}
