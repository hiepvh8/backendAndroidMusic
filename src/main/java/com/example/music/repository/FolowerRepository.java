package com.example.music.repository;

import com.example.music.model.entity.Folower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolowerRepository extends JpaRepository<Folower, Long> {
    @Query("SELECT f FROM Folower f WHERE f.foloweduserid = :folowedUserId AND f.user.id = :userId")
    Optional<Folower> findByFolowedUserIdAndUser_Id(@Param("folowedUserId") Long folowedUserId, @Param("userId") Long userId);

    void deleteByUserIdAndFoloweduserid(Long userId, Long folowedUserId);
}
