package com.example.music.repository;

import com.example.music.model.entity.PlayListSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlayListSong,Long> {
}
