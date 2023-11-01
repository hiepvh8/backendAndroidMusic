package com.example.music.controller;

import com.example.music.Enum.Genre;
import com.example.music.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bài Hát", description = "")
@RestController
@RequestMapping("/song")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @Operation(
            summary = "client gửi GetMethod trả về danh sách tất cả các baì hát.",
            description = ""
    )
    @GetMapping("")
    public ResponseEntity<?> getAllSong(){
        return ResponseEntity.ok(songService.getAllSong());
    }

    @Operation(
            summary = "client gửi GetMethod trả về danh sách theo thể loại truyền vào : dùng param để truyền",
            description = ""
    )
    @GetMapping("/genre")
    public ResponseEntity<?> getAllSongForGenre(@RequestParam Genre genre){
        return ResponseEntity.ok(songService.getAllSongForGenre(genre));
    }
}
