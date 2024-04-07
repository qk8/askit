package com.gmail.kayrakan007.postservice.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gmail.kayrakan007.postservice.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    List<Post> findByParentId(Long parentId, Pageable pageable);

    List<Post> findByParentIdAndCreatedAtGreaterThan(Long parentId, Instant since, Pageable pageable);

    @Modifying
	@Query("UPDATE Post p SET p.totalVotes = (p.totalVotes + 1) WHERE p.id = :postId")
	void increaseTotalVotes(@Param("postId") Long id);
	
	@Modifying
	@Query("UPDATE Post p SET p.totalVotes = (p.totalVotes - 1) WHERE p.id = :postId")
	void decreaseTotalVotes(@Param("postId") Long id);
	
}
