package com.example.music.service;

import com.example.music.model.dto.SignupDTO;
import com.example.music.model.entity.User;

import java.util.List;

public interface UserService {
    public Boolean createUser(SignupDTO userDto);
    public List<User> getAllUser();
}
