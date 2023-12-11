package com.example.music.controller;

import com.example.music.exception.NotFoundException;
import com.example.music.model.dto.SignupDTO;
import com.example.music.model.dto.UserDTOAll;
import com.example.music.model.dto.UserUpdate;
import com.example.music.model.entity.User;
import com.example.music.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "User", description = "")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/email")
    public ResponseEntity<UserDTOAll> getUserbyEmail(@RequestParam String email){
        try{
            return ResponseEntity.ok(userService.getUserDTOAll(email));
        }catch(Exception e){
            throw new NotFoundException("Người dùng không tồn tại!");
        }
    }

    @GetMapping("/get/username")
    public ResponseEntity<UserDTOAll> getUserbyUsername(@RequestParam String username){
        try{
            return ResponseEntity.ok(userService.getUserDTOAllbyUsername(username));
        }catch(Exception e){
            throw new NotFoundException("Người dùng không tồn tại!");
        }
    }
    //
    @PutMapping("/update")
    public ResponseEntity<?> updateUserProfileByUsername(@RequestParam String username, @RequestBody UserUpdate userUpdate) {
        userService.updateUserProfile(username, userUpdate);
        return ResponseEntity.ok("Thành Công !");
    }
}
