package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @GetMapping("/list")
  public List<Post> postList(@RequestParam(required = false, defaultValue = "") String title) {
    return postService.postList(title);
  }

  @GetMapping("/{postId}")
  public Post postDetail(@PathVariable @Validated Integer postId) {
    return new Post(1L, "title1", "content", "nomad.coding", LocalDateTime.now());
  }

}
