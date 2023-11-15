package com.example.music.repository;

import com.example.music.Enum.Genre;
import com.example.music.model.entity.Album;
import com.example.music.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findByUserId(Long userId);
}
