package com.example.music.serviceImp;

import com.example.music.Enum.Genre;
import com.example.music.model.entity.Song;
import com.example.music.repository.SongRepository;
import com.example.music.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImp implements SongService {
    private final SongRepository songRepository;

    public SongServiceImp(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getAllSong(){
        return songRepository.findAll();
    }

    public List<Song> getAllSongForGenre(Genre genre){
        return songRepository.findByGenre(genre);
    }
}
