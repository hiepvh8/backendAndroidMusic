package com.example.music.serviceImp;

import com.example.music.model.dto.UserProfileDTO;
import com.example.music.model.entity.Song;
import com.example.music.service.AlbumService;
import com.example.music.service.FolowerService;
import com.example.music.service.SongService;
import com.example.music.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserProfileServiceImp implements UserProfileService {

    private final FolowerService folowerService;
    private final SongService songService;
    private final AlbumService albumService;

    public UserProfileServiceImp(FolowerService folowerService, SongService songService, AlbumService albumService) {
        this.folowerService = folowerService;
        this.songService = songService;
        this.albumService = albumService;
    }

    public CompletableFuture<Boolean> checkFollowerStatus(Long viewerId, Long targetUserId) {
        return CompletableFuture.supplyAsync(() -> folowerService.isFollowing(viewerId, targetUserId));
    }

    public CompletableFuture<List<Song>> getSongs(Long userId) {
        return CompletableFuture.supplyAsync(() -> songService.getSongsByAlbumId(albumService.getAlbumIdByUserId(userId)));
    }

    public UserProfileDTO getUserProfileData(Long viewerId, Long targetUserId) {
        CompletableFuture<Boolean> followerStatusFuture = checkFollowerStatus(viewerId, targetUserId);
        CompletableFuture<List<Song>> songsFuture = getSongs(targetUserId);

        return followerStatusFuture
                .thenCombine(songsFuture, UserProfileDTO::new)
                .join();
    }
    //==========================

    public CompletableFuture<Boolean> checkFollowerStatusbyUsername(Long viewerId, String targetUsername) {
        return CompletableFuture.supplyAsync(() -> folowerService.isFollowingbyUsername(viewerId, targetUsername));
    }

    public CompletableFuture<List<Song>> getSongsbyUsername(String targetUsername) {
        return CompletableFuture.supplyAsync(() -> songService.getSongsByAlbumId(albumService.getAlbumIdByUsername(targetUsername)));
    }

    public UserProfileDTO getUserProfileDatabyUsername(Long viewerId, Long targetUsername) {
        CompletableFuture<Boolean> followerStatusFuture = checkFollowerStatus(viewerId, targetUsername);
        CompletableFuture<List<Song>> songsFuture = getSongs(targetUsername);

        return followerStatusFuture
                .thenCombine(songsFuture, UserProfileDTO::new)
                .join();
    }
}
