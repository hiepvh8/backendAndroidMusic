package com.example.music.controller;

import com.example.music.model.entity.Folower;
import com.example.music.model.entity.User;
import com.example.music.service.FolowerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Folower", description = "")
@RestController
@RequestMapping("/folower")
public class FolowerController {

    private final FolowerService folowerService;

    public FolowerController(FolowerService folowerService) {
        this.folowerService = folowerService;
    }

    @PostMapping("/follow")
    public ResponseEntity<Folower> followUser(@RequestParam Long followerId, @RequestParam String followedUsername) {
        return ResponseEntity.ok(folowerService.followUser(followerId,followedUsername));
    }

    @GetMapping("/check/{followerId}/{followedUserId}")
    public boolean isFollowing(@PathVariable Long followerId, @PathVariable Long followedUserId) {
        return folowerService.isFollowing(followerId, followedUserId);
    }
}
