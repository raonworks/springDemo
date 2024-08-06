package com.example.demo.services;

import com.example.demo.models.dto.DtoPostResponse;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.PostResponseMapper;
import com.example.demo.repositories.PostRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
//  private final PostMapper postMapper;
  private final PostResponseMapper postResponseMapper;

  public List<DtoPostResponse> postList(String title) {
//    return postRepository.findAll();
//    return postMapper.postList();
    if(title.isEmpty()) return postRepository.findAll().stream().map(postResponseMapper).toList();
    return postRepository.findByTitleContains(title).stream().map(postResponseMapper).toList();
  }

}
