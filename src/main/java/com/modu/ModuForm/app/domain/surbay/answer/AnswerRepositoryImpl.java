package com.modu.ModuForm.app.domain.surbay.answer;

import com.modu.ModuForm.app.domain.surbay.QSurvey;
import com.modu.ModuForm.app.exception.nosuch.NoSuchAnswerIdException;
import com.modu.ModuForm.app.web.dto.answer.AnswerCheck;
import com.modu.ModuForm.app.web.dto.answer.AnswerResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.modu.ModuForm.app.domain.surbay.answer.QAnswer.answer;

public class AnswerRepositoryImpl implements AnswerRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public AnswerRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public AnswerResponse getAnswerResponse(Long id) throws NoSuchAnswerIdException {
        AnswerCheck answerCheck = getAnswerCheck(id).orElseThrow(NoSuchAnswerIdException::new);
        AnswerResponse answerResponse = queryFactory.select(Projections.constructor(AnswerResponse.class, answer.id, answer.anonymousFlag))
                .from(answer)
                .where(answer.id.eq(id))
                .fetchOne();
        answerResponse.setAnswerCheck(answerCheck);
        return answerResponse;
    }

    private Optional<AnswerCheck> getAnswerCheck(Long id) {
        return Optional.ofNullable(
                queryFactory.select(
                        Projections.constructor(AnswerCheck.class, answer.survey, answer.answerDataList))
                .from(answer)
                .join(answer.survey, QSurvey.survey).fetchJoin()
                .where(answer.id.eq(id))
                .fetchOne());
    }
}
