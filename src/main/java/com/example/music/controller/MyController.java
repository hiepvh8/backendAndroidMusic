package com.example.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/my-url")
    public String myHandler() {
        // Xử lý logic ở đây
        return "my-page";
    }
}
