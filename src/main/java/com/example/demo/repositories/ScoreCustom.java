package com.example.demo.repositories;

import com.example.demo.models.dto.DtoScoreResponse;

import java.util.List;

public interface ScoreCustom {
    List<DtoScoreResponse> findScoresByStudent(Long studentId);
}
