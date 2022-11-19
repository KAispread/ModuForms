package com.modu.ModuForm.app.service.survey;

import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TestPropertySource(value = "classpath:application-test-db.properties")
@SpringBootTest
public class AnswerServiceImplTest {
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerServiceImpl answerService;
}