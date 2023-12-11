package com.example.music.controller;

import com.example.music.exception.NotFoundException;
import com.example.music.model.entity.Folower;
import com.example.music.model.entity.User;
import com.example.music.service.FolowerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> followUser(@RequestParam Long followerId, @RequestParam String followedUsername) {
        try{
            boolean l = folowerService.followUser(followerId,followedUsername);
            if(l){
                return ResponseEntity.ok("Follower thành công");
            }else{
                return ResponseEntity.ok("Người dùng đã được follower rồi.");
            }
        }catch (Exception e){
            throw new NotFoundException(" Người dùng đã được follower rồi. ");
        }
    }
    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollowUser(@RequestParam Long followerId, @RequestParam String followedUsername) {
        try{
            boolean l = folowerService.unfollowUser(followerId,followedUsername);
            if(l){
                return ResponseEntity.ok("UnFollower thành công");
            }else{
                return ResponseEntity.ok("Đang không follower người dùng này.");
            }
        }catch (Exception e){
            throw new NotFoundException("UnFollower không thành công!");
        }
    }


    @GetMapping("/check/{followerId}/{followedUserId}")
    public boolean isFollowing(@PathVariable Long followerId, @PathVariable Long followedUserId) {
        return folowerService.isFollowing(followerId, followedUserId);
    }
}
