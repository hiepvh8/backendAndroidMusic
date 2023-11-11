package com.example.music.serviceImp;

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
    @Transactional
    public Folower followUser(Long followerId, String followedUsername) {
        User follower = userRepository.findById(followerId).orElse(null);
        User followedUser = userRepository.findByUsername(followedUsername).orElse(null);

        if (follower != null && followedUser != null) {
            Folower folower = new Folower();
            folower.setUser(follower);
            folower.setFoloweduserid(followedUser.getId());
            follower.getFolowers().add(folower);
            userRepository.save(follower);
            return folower;
        }

        return null;
    }

    @Override
    public boolean isFollowing(Long followerId, Long followedUserId) {
        Optional<Folower> folower = folowerRepository.findByFolowedUserIdAndUser_Id(followedUserId, followerId);
        return folower.isPresent();
    }
}
