package com.example.music.service;

import com.example.music.model.dto.SignupDTO;
import com.example.music.model.dto.UserDTOAll;
import com.example.music.model.dto.UserUpdate;
import com.example.music.model.entity.User;

import java.util.List;

public interface UserService {
//    public Boolean createUser(SignupDTO userDto);

    // check xem tên username hoặc email đã tồn tại hay chưa
    int isUserNameValid(String username, String email);

    public List<User> getAllUser();

    User getUser(String emai);


    UserDTOAll getUserDTOAll(String emai);

    UserDTOAll getUserDTOAllbyUsername(String username);


    User updateUserProfile(String username, UserUpdate userUpdate);
}
