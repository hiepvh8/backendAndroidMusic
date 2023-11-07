package com.example.music.controller;

import com.example.music.model.dto.AuthResponse;
import com.example.music.model.dto.SigninDTO;
import com.example.music.model.dto.SignupDTO;
import com.example.music.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Đăng kí / Đăng nhập", description = "truy cập không cần token")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "client gửi PostMethod yêu cầu tạo tài khoản",
            description = ""
    )
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupDTO signupDTO){
        return ResponseEntity.ok(authService.register(signupDTO));
    }

    @Operation(
            summary = "client gửi PostMethod yêu cầu tạo tài khoản",
            description = ""
    )
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody SigninDTO signinDTO) {
        return ResponseEntity.ok(authService.login(signinDTO));
    }

    @PutMapping("/changepassword")
    public ResponseEntity<AuthResponse> changePassword(@RequestBody SigninDTO signinDTO){
        return ResponseEntity.ok(authService.resetPassword(signinDTO));
    }

    //Chưa xử lý xóa đc
    @DeleteMapping ("/delete/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        authService.deleteUserById(userId);
    }
}
