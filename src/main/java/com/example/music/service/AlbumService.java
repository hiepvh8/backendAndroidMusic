package com.example.music.service;

import com.example.music.model.entity.Album;

public interface AlbumService {
    public Album getAlbumById(Long albumId);

    Long getAlbumIdByUserId(Long userId);
}
