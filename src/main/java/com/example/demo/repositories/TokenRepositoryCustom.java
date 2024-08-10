package com.example.demo.repositories;

import com.example.demo.models.Token;

import java.util.List;

public interface TokenRepositoryCustom {
    List<Token> findAllValidTokenByUser(String email);
}
