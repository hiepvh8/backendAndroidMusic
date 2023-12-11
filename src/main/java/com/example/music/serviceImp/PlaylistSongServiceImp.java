package com.example.music.serviceImp;

import com.example.music.exception.NotFoundException;
import com.example.music.model.entity.PlayList;
import com.example.music.model.entity.PlayListSong;
import com.example.music.model.entity.Song;
import com.example.music.repository.PlaylistRepository;
import com.example.music.repository.PlaylistSongRepository;
import com.example.music.repository.SongRepository;
import com.example.music.service.PlayListSongService;
import org.springframework.stereotype.Repository;

@Repository
public class PlaylistSongServiceImp implements PlayListSongService {
    private final PlaylistSongRepository playlistSongRepository;
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;

    public PlaylistSongServiceImp(PlaylistSongRepository playlistSongRepository, PlaylistRepository playlistRepository, SongRepository songRepository) {
        this.playlistSongRepository = playlistSongRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public void addSongToPlaylist(Long songId, Long playlistId) {
        PlayList playList = playlistRepository.findById(playlistId).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);
        if(playList == null || song == null){
            throw new NotFoundException("Thêm bài hát vào Playlist Không thành công!");
        }else{
            playList.setNumberSong(playList.getNumberSong()+1);
            playList.setDuration(playList.getDuration()+song.getDuration());
            playlistRepository.save(playList);
            PlayListSong playListSong = new PlayListSong();
            playListSong.setPlayList(playList);
            playListSong.setSong(song);

            playlistSongRepository.save(playListSong);
        }
    }
}
