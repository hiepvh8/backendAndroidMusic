package com.example.music.repository;

import com.example.music.Enum.Genre;
import com.example.music.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByGenre(Genre genre);
}
