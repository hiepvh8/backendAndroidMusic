package com.example.music.repository;

import com.example.music.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeadBackRepository extends JpaRepository<Comment, Long> {
}
