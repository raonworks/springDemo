package com.example.demo.repositories.implement;

import com.example.demo.models.QScore;
import com.example.demo.models.QStudent;
import com.example.demo.models.dto.DtoScoreResponse;
import com.example.demo.repositories.ScoreCustom;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ScoreCustomImpl implements ScoreCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<DtoScoreResponse> findScoresByStudent(Long studentId) {

        QStudent st = QStudent.student;
        QScore sc = QScore.score1;

        JPAQuery<Tuple> query = queryFactory
                .select(
                    st.id,
                    st.name,
                    st.age,
                    sc.subject,
                    sc.score
                )
                .from(st)
                .join(sc)
                .on(st.id.eq(sc.studentId))
                .where(st.id.eq(studentId))
                .orderBy(st.id.asc());

        return query.fetchJoin().fetch().stream().map(tuple ->
                DtoScoreResponse.builder()
                        .id(tuple.get(st.id))
                        .name(tuple.get(st.name))
                        .age(tuple.get(st.age))
                        .subject(tuple.get(sc.subject))
                        .score(tuple.get(sc.score))
                        .build()).toList();
    }
}
