package com.modu.ModuForm.app.service;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
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
    public UserFormDetails getUserFormDetails(Pageable surveyPage, Integer currentSurveyPage,
                                              Pageable answerPage, Integer currentAnswerPage, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        Page<Survey> surveyPageList = surveyRepository.findAllByUser(user, surveyPage);
        Page<Answer> answerPageList = answerRepository.findAllByUser(user, answerPage);

        return new UserFormDetails(user, surveyPageList, currentSurveyPage, answerPageList, currentAnswerPage);
    }
}
