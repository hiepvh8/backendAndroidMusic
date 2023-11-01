package com.example.music.service;

import com.example.music.model.dto.SignUp;
import com.example.music.model.entity.User;

import java.util.List;

public interface UserService {
    public Boolean createUser(SignUp userDto);
    public List<User> getAllUser();
}
