package com.example.music.serviceImp;

import com.example.music.exception.NotFoundException;
import com.example.music.model.entity.Folower;
import com.example.music.model.entity.User;
import com.example.music.repository.FolowerRepository;
import com.example.music.repository.UserRepository;
import com.example.music.service.FolowerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FolowerServiceImp implements FolowerService {

    private final UserRepository userRepository;
    private final FolowerRepository folowerRepository;

    public FolowerServiceImp(UserRepository userRepository, FolowerRepository folowerRepository) {
        this.userRepository = userRepository;
        this.folowerRepository = folowerRepository;
    }

   @Override
    public boolean followUser(Long followerId, String followedUsername) {
        // Lấy thông tin người dùng cần theo dõi
        User userToFollow = userRepository.findByUsername(followedUsername).orElse(null);
        User user = userRepository.findById(followerId).orElse(null);
        if (userToFollow == null) {
            return false;
        }else{
            // Kiểm tra xem đã follow chưa
            if (!isFollowing(followerId, userToFollow.getId())) {
                // Tạo một đối tượng Folower mới và lưu vào cơ sở dữ liệu
                Folower folower = new Folower();
                folower.setFoloweduserid(userToFollow.getId());
                folower.setUser(user);
                folowerRepository.save(folower);
                return true;
            }else{
                return false;
            }
        }
    }

    @Transactional
    @Override
    public boolean unfollowUser(Long followerId, String unfollowedUsername) {
        // Xóa theo dõi bằng cách xóa bản ghi từ bảng folower
        User userToUnFollow = userRepository.findByUsername(unfollowedUsername).orElse(null);
        if(userToUnFollow == null){
           return false;
        }else{
            if(isFollowing(followerId, userToUnFollow.getId())){
                folowerRepository.deleteByUserIdAndFoloweduserid(followerId, userToUnFollow.getId());
               return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public boolean isFollowing(Long followerId, Long followedUserId) {
        Optional<Folower> folower = folowerRepository.findByFolowedUserIdAndUser_Id(followedUserId, followerId);
        return folower.isPresent();
    }

    @Override
    public boolean isFollowingbyUsername(Long followerId, String followedUsername) {
        User user = userRepository.findByUsername(followedUsername).orElse(null);
        if(user == null){
            throw new NotFoundException("Người dùng không tồn tại/");
        }else{
            Optional<Folower> folower = folowerRepository.findByFolowedUserIdAndUser_Id(user.getId(), followerId);
            return folower.isPresent();
        }
    }
}
