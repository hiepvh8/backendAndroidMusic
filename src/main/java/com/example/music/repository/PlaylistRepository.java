package com.example.music.repository;

import com.example.music.Enum.Genre;
import com.example.music.model.entity.PlayList;
import com.example.music.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<PlayList, Long> {
   //List<PlayList> findByUsername(Genre genre);
}
