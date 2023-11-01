package com.example.music.repository;

import com.example.music.Enum.Genre;
import com.example.music.model.entity.Album;
import com.example.music.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
