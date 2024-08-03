package com.example.demo.services;

import com.example.demo.mapper.PostMapper;
import com.example.demo.models.Post;
import com.example.demo.repositories.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;

  public List<Post> postList(String title) {
//    return postRepository.findAll();
//    return postMapper.postList();
    if(title.isEmpty()) return postRepository.findAll();
    System.out.println(title);
    return postRepository.findByTitleContains(title);
  }

}
