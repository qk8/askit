package com.gmail.kayrakan007.voteservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.kayrakan007.voteservice.entity.VoteType;

@Repository
public interface VoteTypeRepository extends JpaRepository<VoteType, Long> {

    Optional<VoteType> findByName(String name);

}
