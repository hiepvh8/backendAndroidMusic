package com.example.music.controller;

import com.example.music.exception.NotFoundException;
import com.example.music.model.dto.AlbumDTOAll;
import com.example.music.model.entity.Album;
import com.example.music.service.AlbumService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Album", description = "")
@RestController
@RequestMapping("/album")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTOAll> getAlbumDTOAllById(@PathVariable Long id) {
        AlbumDTOAll albumDTOAll = albumService.getAlbumDTOAllById(id);
            if (albumDTOAll != null) {
                return new ResponseEntity<>(albumDTOAll, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

}
