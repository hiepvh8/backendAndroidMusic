package com.example.music.service;

import com.example.music.model.entity.Folower;
import com.example.music.model.entity.User;
import jakarta.transaction.Transactional;

public interface FolowerService {
    boolean followUser(Long followerId, String followedUsername);

    boolean unfollowUser(Long followerId, String unfollowedUsername);

    boolean isFollowing(Long followerId, Long followedUserId);

    boolean isFollowingbyUsername(Long followerId, String followedUsername);
}
