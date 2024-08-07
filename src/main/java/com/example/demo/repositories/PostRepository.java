package com.example.demo.repositories;

import com.example.demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findByTitle(String title);
  List<Post> findByTitleContains(String title);
}
