package com.example.music.serviceImp;

import com.example.music.model.entity.Album;
import com.example.music.repository.AlbumRepository;
import com.example.music.service.AlbumService;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImp implements AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumServiceImp(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album getAlbumById(Long albumId){
        return albumRepository.getAlbumById(albumId);
    }
}
