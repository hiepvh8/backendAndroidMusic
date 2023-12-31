package com.example.music.controller;

import com.example.music.Enum.Genre;
import com.example.music.model.dto.SongDTO;
import com.example.music.model.dto.SongDTOAll;
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

import java.util.List;

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

    @Operation(
            summary = "client gửi PostMethod yêu cầu tạo bài hát. Dùng sesion để call api aupdateall",
            description = "Lúc này chỉ thêm các trường cơ bản songDTO, chưa có ảnh và audio. sau khi tạo bài hát gồm các trường cơ bản rồi mới thêm ảnh và file audio sau. Sau khi thực hiện api này thì phải thực hiện luôn api apdateall để upload ảnh và audio. Dùng session. "
    )
    @PostMapping("/add")
    public ResponseEntity<String> addSong(@RequestBody SongDTO songDTO, @RequestParam("albumId") Long albumId, HttpSession session) {

        Song song = songService.addSong(songDTO, albumId);
        Long id = song.getId();

        // Lưu trữ ID trong phiên làm việc
        session.setAttribute("yourKeyForID", id);

        return ResponseEntity.ok("Thành Công");
    }

    @Operation(
            summary = "client gửi PostMethod để upload nốt ảnh và audio trong quá trình thêm 1 bài hát ",
            description = ""
    )
    @PutMapping("/updateall")
    public ResponseEntity<String> updateallSong(@RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio, HttpSession session){
        // Lấy ID từ phiên làm việc
        Long id = (Long) session.getAttribute("yourKeyForID");
        // Sử dụng ID cho API thứ hai
        if (id != null) {
            // Thực hiện xử lý với ID
            songService.updateSong(image,audio,id);
            return ResponseEntity.ok("Thành công!");
        } else {
            return ResponseEntity.badRequest().body("ID không tồn tại trong phiên làm việc.");
        }
    }

//    @PostMapping("/xemha")
//    public ResponseEntity<String> xemhinhanh(@RequestParam("image") MultipartFile image){
//        return ResponseEntity.ok(songService.xemTruocAvatar(image));
//    }
//    @PostMapping("/xembh")
//    public ResponseEntity<String> xembaihat(@RequestParam("audio") MultipartFile audio){
//        return ResponseEntity.ok(songService.xemTruocAudio(audio));
//    }

//    @PostMapping("/xemall")
//    public ResponseEntity<String> xemall(@RequestParam("image") MultipartFile image, @RequestParam("audio") MultipartFile audio){
//        return ResponseEntity.ok(songService.xemTruocAll(image,audio).toString());
//    }
@Operation(
        summary = "client gửi GetMethod để tìm bài hát theo tên gần đúng ",
        description = ""
)
    @GetMapping("/search")
    public ResponseEntity<List<SongDTOAll>> searchSongsByPartialTitle(@RequestParam String partialTitle) {
        return ResponseEntity .ok(songService.searchSongsByPartialTitle(partialTitle));
    }

    @Operation(
            summary = "client gửi PostMethod để nghe audio và tăng số lượt nghe lên 1 ",
            description = "Bắt sự kiện api khi ấn nghe nhạc để tăng tượt nghe lên 1"
    )
    @PostMapping("/play/{songId}")
    public ResponseEntity<String> playSong(@PathVariable Long songId) {
        songService.incrementPlayCount(songId);
        return ResponseEntity.ok("Song play count incremented. Thành Công!");
    }

    @Operation(
            summary = "client gửi GetMethod để hiển thị danh sach bai hát nổi bật cs lượt nghe cao ",
            description = ""
    )
    @GetMapping("/topplaycount")
    public ResponseEntity<List<SongDTOAll>> getSongsByPlayCountDescending() {
        List<SongDTOAll> songDTOAlls = songService.getSongsByPlayCountDescending();
        return ResponseEntity.ok(songDTOAlls);
    }
}
