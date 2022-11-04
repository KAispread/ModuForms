package com.modu.ModuForm.app;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.*;
import com.modu.ModuForm.app.service.survey.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyDataInit {
    public User userInit(UserRepository userRepository) {
        User user = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rachel")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("asdf1234@naver.com")
                .phone("012312123")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());
        userRepository.save(user);
        return user;
    }

    public Access accessInit(AccessRepository accessRepository ,User user) {
        Access access = Access.builder()
                .user(user)
                .userId("pppp1234")
                .password("qwe123")
                .build();
        accessRepository.save(access);
        return access;
    }

    public List<SurveyQuestion> surveyQuestionInit() {
        // 질문 추가
        List<SurveyQuestion> surveyQuestions = new ArrayList<>();
        surveyQuestions.add(buildQuestion(0, "이름을 입력해주세요", null , QuesType.SHORT));
        surveyQuestions.add(buildQuestion(1, "나이를 입력해주세요", "20|30|40|50" ,QuesType.MULTIPLE));
        surveyQuestions.add(buildQuestion(2, "주소를 입력해주세요", null ,QuesType.SHORT));
        return surveyQuestions;
    }
    public Survey surveyInit(SurveyRepository surveyRepository ,User user, List<SurveyQuestion> surveyQuestionList) {
        Survey newSurvey = Survey.builder()
                .user(user)
                .title("참여 조사")
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestionList)
                .build();

        newSurvey.setUser(user);
        surveyRepository.save(newSurvey);
        return newSurvey;
    }

    public SurveyQuestion buildQuestion(Integer count, String question, String distractor, QuesType quesType) {
        return SurveyQuestion.builder()
                .number(count)
                .questionType(quesType)
                .question(question)
                .distractor(distractor)
                .build();
    }
}
