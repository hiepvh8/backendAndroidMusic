package com.example.music.controller;

import com.example.music.model.dto.PlayListDTO;
import com.example.music.model.entity.PlayList;
import com.example.music.service.PlaylistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Playlist", description = "")
@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("")
    public ResponseEntity<List<PlayListDTO>> getPlayListsByUser(@RequestParam String username) {
        List<PlayListDTO> playListDTOs = playlistService.getPlayListsByUsername(username);
        return new ResponseEntity<>(playListDTOs, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addPlaylistForUser(@RequestParam String username, @RequestBody PlayListDTO playListDTO) {
        playlistService.addPlaylistForUser(username, playListDTO);
        return ResponseEntity.ok("Thành Công!");
    }
}
