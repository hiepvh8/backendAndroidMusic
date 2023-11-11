package com.example.music.controller;

import com.example.music.model.entity.PlayList;
import com.example.music.model.entity.Song;
import com.example.music.service.PlayListSongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Playlistsong", description = "")
@RestController
@RequestMapping("/playlistsong")
public class PlayListSongController {

    private final PlayListSongService playListSongService;

    public PlayListSongController(PlayListSongService playListSongService) {
        this.playListSongService = playListSongService;
    }

    @Operation(
            summary = "client gửi PostMethod Thêm bài hát vào playlist.",
            description = ""
    )
    @PostMapping("/addSongtoPlaylist/song/{songId}/playList/{playListId}")
    public ResponseEntity<String> addSongToPlaylist(@PathVariable Long songId, @PathVariable Long playListId) {
        try{
            playListSongService.addSongToPlaylist(songId,playListId);
            return ResponseEntity.ok("Song added to playlist successfully.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Invalid playlist or song ID.");
        }

    }
}
