package com.gmail.kayrakan007.tagservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gmail.kayrakan007.tagservice.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
	
	@Modifying
	@Query("UPDATE Tag t SET t.postQuantity = (t.postQuantity + 1) WHERE t.id = :tagId")
	void increaseQuantity(@Param("tagId") Long id);
	
	@Modifying
	@Query("UPDATE Tag t SET t.postQuantity = (t.postQuantity - 1) WHERE t.id = :tagId")
	void decreaseQuantity(@Param("tagId") Long id);

}
