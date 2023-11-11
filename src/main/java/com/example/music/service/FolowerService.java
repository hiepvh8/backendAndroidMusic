package com.example.music.service;

import com.example.music.model.entity.Folower;
import com.example.music.model.entity.User;
import jakarta.transaction.Transactional;

public interface FolowerService {
    @Transactional
    Folower followUser(Long followerId, String followedUsername);

    boolean isFollowing(Long followerId, Long followedUserId);
}
