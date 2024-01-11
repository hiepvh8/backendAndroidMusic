package com.example.music.controller;

import com.example.music.model.dto.SongDTOAll;
import com.example.music.model.entity.Song;
import com.example.music.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private SongService songService; // Giả sử bạn có một service để quản lý bài hát

    public HomeController(SongService songService){
        this.songService = songService;
    }
    @GetMapping("")
    public String home(Model model) {
        List<SongDTOAll> songs = songService.getAllSong(); // Lấy danh sách bài hát từ service
        model.addAttribute("songs", songs);
        return "home"; // Trả về tên của file HTML (home.html)
    }
}
