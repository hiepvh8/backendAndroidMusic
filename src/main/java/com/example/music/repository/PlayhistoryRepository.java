package com.example.music.repository;

import com.example.music.model.entity.PlayHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayhistoryRepository extends JpaRepository<PlayHistory, Long> {
}
