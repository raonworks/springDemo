package com.example.demo.repositories.implement;

import com.example.demo.models.QToken;
import com.example.demo.models.Token;
import com.example.demo.repositories.TokenRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TokenRepositoryCustomImpl implements TokenRepositoryCustom {

    private final JPAQueryFactory queryFactory;

//    //다중 데이터베이스 활용시 적용
//    public TokenRepositoryCustomImpl( @Qualifier("secondaryJpaQueryFactory") JPAQueryFactory queryFactory) {
//        this.queryFactory = queryFactory;
//    }

    @Override
    public List<Token> findAllValidTokenByUser(String email) {
        QToken t = QToken.token1;

        JPAQuery<Tuple> query = queryFactory
                .select(t.id, t.token, t.tokenType, t.expired, t.revoked, t.email)
                .from(t)
                .where(t.email.eq(email));

        return query.fetch()
                .stream().map(tuple ->
                        Token.builder()
                                .id(tuple.get(t.id))
                                .token(tuple.get(t.token))
                                .tokenType(tuple.get(t.tokenType))
                                .expired(Boolean.TRUE.equals(tuple.get(t.expired)))
                                .revoked(Boolean.TRUE.equals(tuple.get(t.revoked)))
                                .email(tuple.get(t.email))
                                .build()
                ).toList();

    }
}
