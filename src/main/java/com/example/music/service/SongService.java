package com.example.music.service;

import com.example.music.Enum.Genre;
import com.example.music.model.entity.Song;

import java.util.List;

public interface SongService {
    public List<Song> getAllSong();
    public List<Song> getAllSongForGenre(Genre genre);
}
