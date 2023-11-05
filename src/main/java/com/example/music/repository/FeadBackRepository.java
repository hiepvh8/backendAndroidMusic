package com.example.music.repository;

import com.example.music.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeadBackRepository extends JpaRepository<Comment, Long> {
}
