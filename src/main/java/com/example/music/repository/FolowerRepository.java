package com.example.music.repository;

import com.example.music.model.entity.Folower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolowerRepository extends JpaRepository<Folower, Long> {
}
