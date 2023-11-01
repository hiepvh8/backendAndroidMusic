package com.example.music.controller;

import com.example.music.model.dto.SignUp;
import com.example.music.model.entity.User;
import com.example.music.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUp userDto){
        if(userService.createUser(userDto)){
           // String jwt = jwtService.generateToken(new Account(accountDto));
            return new ResponseEntity<>("Thành Công"
                    , HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>("Thất Bại",
                HttpStatus.valueOf(200));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUser(){
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }
}
