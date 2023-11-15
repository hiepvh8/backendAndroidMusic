package com.example.music.controller;

import com.example.music.model.dto.UserProfileDTO;
import com.example.music.model.entity.Song;
import com.example.music.serviceImp.UserProfileServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Tag(name = "UserProfile", description = "")
@RestController
@RequestMapping("/userprofile")
public class UserProfileController {
    private final UserProfileServiceImp userProfileService;

    public UserProfileController(UserProfileServiceImp userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Operation(
            summary = "Api này call để khi nhấn xem profile của người dúng khác.",
            description = "api gọi để check xem người dùng đã đc folower chưa và hiển thị danh sách các bài hát trong album."
    )
    @GetMapping("/profile/{viewerId}/{targetUserId}")
    public UserProfileDTO getUserProfileData(
            @PathVariable Long viewerId,
            @PathVariable Long targetUserId) {

        CompletableFuture<Boolean> followerStatusFuture = userProfileService.checkFollowerStatus(viewerId, targetUserId);
        CompletableFuture<List<Song>> songsFuture = userProfileService.getSongs(targetUserId);

        return followerStatusFuture
                .thenCombine(songsFuture, UserProfileDTO::new)
                .join();
    }
    @GetMapping("/profile/username/{viewerId}/{targetUsername}")
    public UserProfileDTO getUserProfileDatabyUsername(
            @PathVariable Long viewerId,
            @PathVariable String targetUsername) {

        CompletableFuture<Boolean> followerStatusFuture = userProfileService.checkFollowerStatusbyUsername(viewerId, targetUsername);
        CompletableFuture<List<Song>> songsFuture = userProfileService.getSongsbyUsername(targetUsername);

        return followerStatusFuture
                .thenCombine(songsFuture, UserProfileDTO::new)
                .join();
    }
}
