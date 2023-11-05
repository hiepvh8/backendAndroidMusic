package com.example.music.controller;

import com.example.music.Enum.Genre;
import com.example.music.model.dto.SongDTO;
import com.example.music.model.entity.Album;
import com.example.music.model.entity.Song;
import com.example.music.service.AlbumService;
import com.example.music.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Bài Hát", description = "")
@RestController
@RequestMapping("/song")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
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

//    @PostMapping("/add")
//    public ResponseEntity<String> addSong(@RequestBody SongDTO songDTO,
//                                          @RequestParam("imageFile") MultipartFile imageFile,
//                                          @RequestParam("audioFile") MultipartFile audioFile) {
//
//        songService.addSong(songDTO,imageFile,audioFile);
//
//        return ResponseEntity.ok("Thành Công");
//    }

//    @PostMapping("/add")
//    public ResponseEntity<String> addSong(@RequestBody SongDTO songDTO, @RequestParam("albumId") Long albumId, HttpSession session) {
//
//        Song song = songService.addSong(songDTO, albumId);
//        Long id = song.getId();
//
//        // Lưu trữ ID trong phiên làm việc
//        session.setAttribute("yourKeyForID", id);
//
//        return ResponseEntity.ok("Thành Công");
//    }
//
//    @PutMapping("/updateall")
//    public ResponseEntity<String> updateallSong(@RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio, HttpSession session){
//        // Lấy ID từ phiên làm việc
//        Long id = (Long) session.getAttribute("yourKeyForID");
//        // Sử dụng ID cho API thứ hai
//        if (id != null) {
//            // Thực hiện xử lý với ID
//            songService.updateSong(image,audio,id);
//            return ResponseEntity.ok("Thành công!");
//        } else {
//            return ResponseEntity.badRequest().body("ID không tồn tại trong phiên làm việc.");
//        }
//    }

    @PostMapping("/xemha")
    public ResponseEntity<String> xemhinhanh(@RequestParam("image") MultipartFile image){
        return ResponseEntity.ok(songService.xemTruocAvatar(image));
    }
    @PostMapping("/xembh")
    public ResponseEntity<String> xembaihat(@RequestParam("audio") MultipartFile audio){
        return ResponseEntity.ok(songService.xemTruocAudio(audio));
    }

    @PostMapping("/xemall")
    public ResponseEntity<String> xemall(@RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio){
        return ResponseEntity.ok(songService.xemTruocAll(image,audio).toString());
    }
}
