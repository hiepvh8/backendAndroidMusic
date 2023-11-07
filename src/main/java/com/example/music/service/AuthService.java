package com.example.music.service;

import com.example.music.model.dto.AuthResponse;
import com.example.music.model.dto.SigninDTO;
import com.example.music.model.dto.SignupDTO;

public interface AuthService {
    public void deleteUserById(Long userId);

    //    public Boolean createUser(SignupDTO userDto);
//    public AuthResponse searchUser(SigninDTO signinDTO);
public AuthResponse register(SignupDTO signupDTO);
    public AuthResponse login(SigninDTO signinDTO);
    public AuthResponse resetPassword(SigninDTO signinDTO);
}
