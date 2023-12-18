package com.example.music.serviceImp;

import com.example.music.model.dto.PlayListDTO;
import com.example.music.model.entity.PlayList;
import com.example.music.model.entity.User;
import com.example.music.repository.PlaylistRepository;
import com.example.music.repository.UserRepository;
import com.example.music.service.PlaylistService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImp implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    public PlaylistServiceImp(PlaylistRepository playlistRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<PlayListDTO> getPlayListsByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            List<PlayList> playLists = user.get().getPlayLists();

            List<PlayListDTO> playListDTOs = new ArrayList<>();
            for (PlayList playList : playLists) {
                PlayListDTO playListDTO = new PlayListDTO();
                playListDTO.setId(playList.getId());
                playListDTO.setDuration(playList.getDuration());
                playListDTO.setTimestamp(playList.getTimestamp());
                playListDTO.setTitle(playList.getTitle());
                // Các thuộc tính khác của PlayListDTO có thể cần được ánh xạ tương ứng từ PlayList

                playListDTOs.add(playListDTO);
            }
            return playListDTOs;
        }
        return Collections.emptyList(); // Hoặc có thể trả về null hoặc thông báo lỗi khác tùy theo logic của bạn
    }
    @Override
    public PlayList addPlaylistForUser(String username, PlayListDTO playListDTO) {
        // Tìm người dùng theo username
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            PlayList playList = new PlayList();
            // Liên kết playlist với người dùng
            playList.setUser(user.orElse(null));
            playList.setTitle(playListDTO.getTitle());
            // Set thời gian tạo mới cho playlist
            playList.setTimestamp(LocalDate.now());
            playList.setNumberSong(0);
            playList.setDuration(0);
            // Các logic khác nếu cần

            return playlistRepository.save(playList);
        } else {
            throw new IllegalArgumentException("User with username " + username + " not found.");
        }
    }
}
