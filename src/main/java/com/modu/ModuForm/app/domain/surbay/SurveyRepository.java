package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    List<Survey> findSurveysByUsers(User user);
    @EntityGraph(attributePaths = {"users"}, type = FETCH)
    Page<Survey> findAllByUsers(User user, Pageable pageable);
    void deleteAllByUsers(User user);
}
