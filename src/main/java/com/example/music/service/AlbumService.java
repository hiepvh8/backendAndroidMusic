package com.example.music.service;

import com.example.music.model.dto.AlbumDTOAll;
import com.example.music.model.entity.Album;

public interface AlbumService {
    public Album getAlbumById(Long albumId);

    AlbumDTOAll getAlbumDTOAllById(Long id);

    Long getAlbumIdByUserId(Long userId);

    Long getAlbumIdByUsername(String username);
}
