package com.modu.ModuForm.app.domain.surbay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
