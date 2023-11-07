package com.example.music.repository;

import com.example.music.model.entity.PlayHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayhistoryRepository extends JpaRepository<PlayHistory, Long> {
}
