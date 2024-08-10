package com.example.demo.repositories;

import com.example.demo.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long>, TokenRepositoryCustom {

    Optional<Token> findByToken(String token);
}
