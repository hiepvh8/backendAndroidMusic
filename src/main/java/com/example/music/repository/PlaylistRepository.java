package com.example.music.repository;

import com.example.music.model.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<PlayList, Long> {
}
