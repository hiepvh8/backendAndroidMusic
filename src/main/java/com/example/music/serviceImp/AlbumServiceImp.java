package com.example.music.serviceImp;

import com.example.music.exception.NotFoundException;
import com.example.music.model.dto.AlbumDTOAll;
import com.example.music.model.entity.Album;
import com.example.music.model.entity.User;
import com.example.music.repository.AlbumRepository;
import com.example.music.repository.UserRepository;
import com.example.music.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumServiceImp implements AlbumService {
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    public AlbumServiceImp(AlbumRepository albumRepository, UserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }

    @Override public AlbumDTOAll getAlbumDTOAllById(Long id) {
        Album album = albumRepository.findById(id).orElse(null);
        if(album != null) {
            return new AlbumDTOAll(album);
        }else{
            throw new NotFoundException("Album ko tồn tại/");
        }
    }

    @Override
    public Long getAlbumIdByUserId(Long userId) {
        Album album = albumRepository.findByUserId(userId);
        return album != null ? album.getId() : null;
    }

    @Override
    public Long getAlbumIdByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Album album = albumRepository.findByUserId(user.get().getId());
        return album != null ? album.getId() : null;
    }
}
