package com.example.music.repository;

import com.example.music.Enum.Genre;
import com.example.music.model.entity.Album;
import com.example.music.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    public Song getSongById(Long songId);
    List<Song> findByGenre(Genre genre);
    List<Song> findByTitleContaining(String partialTitle);
    List<Song> findAllByOrderByPlayCountDesc();
    List<Song> findByAlbumId(Long albumId);
}
