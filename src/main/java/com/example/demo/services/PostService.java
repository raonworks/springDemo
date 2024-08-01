package com.example.demo.services;

import com.example.demo.mapper.PostMapper;
import com.example.demo.models.Post;
import com.example.demo.repositories.PostRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;

  public List<Post> postList() {
//    return postRepository.findAll();
    return postMapper.postList();
  }
}
