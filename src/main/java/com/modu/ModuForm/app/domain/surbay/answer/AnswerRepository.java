package com.modu.ModuForm.app.domain.surbay.answer;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @EntityGraph(attributePaths = {"user", "survey"}, type = FETCH)
    Page<Answer> findAllByUser(User user, Pageable pageable);
    void deleteAllBySurvey(Survey survey);
    List<Answer> findAllBySurvey(Survey survey);
}
