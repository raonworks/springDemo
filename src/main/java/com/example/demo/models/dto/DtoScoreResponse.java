package com.example.demo.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DtoScoreResponse {

    private Long id;
    private String name;
    private Integer age;
    private String subject;
    private Integer score;
}
