package com.modu.ModuForm.app.domain.user;

import com.modu.ModuForm.app.web.dto.user.UserDetails;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.modu.ModuForm.app.domain.user.QUser.user;


public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<UserDetails> getDetail(Long id) {
        return Optional.ofNullable(
                queryFactory.select(
                        Projections.constructor(UserDetails.class, user))
                .from(user)
                .where(user.id.eq(id))
                .fetchOne());
    }
}
