package com.example.demo.controllers;

import com.example.demo.models.dto.DtoScoreResponse;
import com.example.demo.services.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/score")
public class ScoreController {
    private final ScoreService scoreService;

    @GetMapping("{studentId}")
    public ResponseEntity<List<DtoScoreResponse>> getScoresByStudent(@PathVariable Long studentId) {
        List<DtoScoreResponse> scoreList = scoreService.findScoresByStudent(studentId);
        return ResponseEntity.ok(scoreList);
    }

}
