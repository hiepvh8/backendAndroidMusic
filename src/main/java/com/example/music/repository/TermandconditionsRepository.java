package com.example.music.repository;

import com.example.music.model.entity.TermAndConditions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermandconditionsRepository extends JpaRepository<TermAndConditions, Long> {
}
