package com.example.demo;

import com.example.demo.models.Post;
import com.example.demo.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

	private final PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Post> list =  List.of(
						new Post(1L, "title1", "content", "nomad.coding", LocalDateTime.now()),
						new Post(2L, "title2", "content", "nomad.coding", LocalDateTime.now()),
						new Post(3L, "title3", "content", "nomad.coding", LocalDateTime.now()),
						new Post(4L, "title4", "content", "nomad.coding", LocalDateTime.now())
		);
		postRepository.saveAll(list);
	}
}