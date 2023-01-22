package com.modu.ModuForm.app.service;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.exception.nosuch.NoSuchUserIdException;
import com.modu.ModuForm.app.web.dto.user.UserFormDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MainPageService {
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final SurveyRepository surveyRepository;

    @PerfLog
    @Transactional(readOnly = true)
    public UserFormDetails getUserFormDetails(Pageable surveyPage, Pageable answerPage, Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchUserIdException::new);
        Page<Survey> surveyPageList = surveyRepository.findAllByUser(user, surveyPage);
        Page<Answer> answerPageList = answerRepository.findAllByUser(user, answerPage);

        return new UserFormDetails(user, surveyPageList, answerPageList);
    }
}
