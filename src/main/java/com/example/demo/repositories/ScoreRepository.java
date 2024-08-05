package com.example.demo.repositories;

import com.example.demo.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long>, ScoreCustom {
}
