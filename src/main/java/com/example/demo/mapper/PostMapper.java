package com.example.demo.mapper;

import com.example.demo.models.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
  public List<Post> postList();
}
