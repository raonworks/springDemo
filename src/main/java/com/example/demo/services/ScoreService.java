package com.example.demo.services;

import com.example.demo.models.dto.DtoScoreResponse;
import com.example.demo.repositories.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public List<DtoScoreResponse> findScoresByStudent(Long studentId) {
        return scoreRepository.findScoresByStudent(studentId);
    }

}
