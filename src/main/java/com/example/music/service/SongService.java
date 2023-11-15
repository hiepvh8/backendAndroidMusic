package com.example.music.service;

import com.example.music.Enum.Genre;
import com.example.music.model.dto.SongDTO;
import com.example.music.model.dto.SongDTOAll;
import com.example.music.model.entity.Song;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SongService {
    public Song getSongById(Long songId);
    public List<SongDTOAll> getAllSong();
    public List<SongDTOAll> getAllSongForGenre(Genre genre);
    //public boolean addSong(SongDTO songDTO, MultipartFile imageFile, MultipartFile audioFile);
    public Song addSong(SongDTO songDTO, Long albumId);
//    public String xemTruocAvatar( MultipartFile file);
//    public String xemTruocAudio( MultipartFile file);
    public List<String> xemTruocAll( MultipartFile imageFile, MultipartFile audioFile);
    public Song updateSong(MultipartFile imageFile, MultipartFile audioFile, Long songId);
    List<SongDTOAll> searchSongsByPartialTitle(String partialTitle);

    public void incrementPlayCount(Long songId);


    List<SongDTOAll> getSongsByPlayCountDescending();

    List<Song> getSongsByAlbumId(Long albumId);
}
