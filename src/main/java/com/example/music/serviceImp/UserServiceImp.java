package com.example.music.serviceImp;

import com.example.music.model.dto.SignupDTO;
import com.example.music.model.entity.Album;
import com.example.music.model.entity.User;
import com.example.music.repository.AlbumRepository;
import com.example.music.repository.UserRepository;
import com.example.music.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;

    public UserServiceImp(UserRepository userRepository, AlbumRepository albumRepository) {
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
    }

    public Boolean createUser(SignupDTO userDto) {
        if (isUserNameValid(userDto.getUsername(), userDto.getEmail())) {
            User newUser = new User(userDto);
            newUser.setPassword(userDto.getPassword());
            Album album = new Album();
            newUser.setAlbum(album);
            album.setUser(newUser);
            userRepository.save(newUser);
            albumRepository.save(album);

            return true;
        }
        return false;
    }
    // check xem tên username hoặc email đã tồn tại hay chưa
    public Boolean isUserNameValid(String username, String email) {
        if (username.equals("") || email.equals("")) return false;
        List<User> list = getAllUser();
        for (User user : list) {
            if (user.getUsername().equals(username) || user.getEmail().equals(email)) return false;
        }
        return true;
    }


    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
