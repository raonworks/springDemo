package com.example.demo.mapper;

import com.example.demo.models.dto.DtoPostResponse;
import com.example.demo.models.Post;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PostResponseMapper implements Function<Post, DtoPostResponse> {

    @Override
    public DtoPostResponse apply(Post post) {
        return DtoPostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
