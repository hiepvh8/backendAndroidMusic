package com.example.music.serviceImp;

import com.example.music.exception.NotFoundException;
import com.example.music.model.dto.UserDTOAll;
import com.example.music.model.dto.UserUpdate;
import com.example.music.model.entity.User;
import com.example.music.repository.AlbumRepository;
import com.example.music.repository.UserRepository;
import com.example.music.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;

    public UserServiceImp(UserRepository userRepository, AlbumRepository albumRepository) {
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
    }

//    public Boolean createUser(SignupDTO userDto) {
//        if (isUserNameValid(userDto.getUsername(), userDto.getEmail())) {
//            User newUser = new User(userDto);
//            newUser.setPassword(userDto.getPassword());
//            Album album = new Album();
//            newUser.setAlbum(album);
//            album.setUser(newUser);
//            userRepository.save(newUser);
//            albumRepository.save(album);
//
//            return true;
//        }
//        return false;
//    }

    @Override
    // check xem tên username hoặc email đã tồn tại hay chưa
    public int isUserNameValid(String username, String email) {
        if (username.equals("") || email.equals("")){
            if(username.equals("")){
                return 1;
            }else{
                return 2;
            }
        }
        List<User> list = getAllUser();
        for (User user : list) {
            if (user.getUsername().equals(username) && user.getEmail().equals(email)){
                return 3;
            }else if(user.getUsername().equals(username) || user.getEmail().equals(email)){
                if(user.getUsername().equals(username)){
                    return 4;
                }else{
                    return 5;
                }
            }
        }
        return 6;
    }


    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String emai){
        Optional<User> user = userRepository.findByEmail(emai);
        if(user.isEmpty()){
            throw new NotFoundException("Người dùng không tồn tại.");
        }else{
            return user.orElse(null);
        }
    }

    @Override
    public UserDTOAll getUserDTOAll(String emai){
        Optional<User> user = userRepository.findByEmail(emai);
        if(user.isEmpty()){
            throw new NotFoundException("Người dùng không tồn tại.");
        }else{
            UserDTOAll userDTOAll = new UserDTOAll(user.orElse(null));
            return userDTOAll;
        }
    }

    @Override
    public UserDTOAll getUserDTOAllbyUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new NotFoundException("Người dùng không tồn tại.");
        }else{
            UserDTOAll userDTOAll = new UserDTOAll(user.orElse(null));
            return userDTOAll;
        }
    }

    @Override
    public User updateUserProfile(String username, UserUpdate userUpdate) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            // Cập nhật thông tin cá nhân
            user.orElse(null).setFullName(userUpdate.getFullName());
            user.orElse(null).setBirthday(userUpdate.getBirthday());
            user.orElse(null).setGender(userUpdate.getGender());
            user.orElse(null).setAddress(userUpdate.getAddress());
            user.orElse(null).setPhoneNumber(userUpdate.getPhoneNumber());

            // Lưu thông tin người dùng đã cập nhật
            return userRepository.save(user.orElse(null));
        } else {
            throw new IllegalArgumentException("User with username " + username + " not found.");
        }
    }
}
