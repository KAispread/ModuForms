package com.modu.ModuForm.app.web;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;
    private final SurveyRepository surveyRepository;

    // 샘플 데이터 생성
    @PostConstruct
    public void init() {
        User user = userRepository.save(User.builder()
                .name("기우")
                .nickName("Kai")
                .birth(19980101L)
                .gender(Gender.MAN)
                .email("asdf1234@naver.com")
                .phone(0103213411L)
                .role(Role.USER)
                .company("AnyangUniv")
                .build());
        accessRepository.save(Access.builder()
                .user(user)
                .userId("ppap012")
                .password("love112")
                .build());
        // 질문 추가
        List<SurveyQuestion> surveyQuestions = new ArrayList<>();
        surveyQuestions.add(buildQuestion(1, "이름을 입력해주세요"));
        surveyQuestions.add(buildQuestion(2, "나이를 입력해주세요"));
        surveyQuestions.add(buildQuestion(3, "주소를 입력해주세요"));

        // 설문 양식 생성
        Survey newSurvey = Survey.builder()
                .user(user)
                .title("참여 조사")
                .postDate(LocalDateTime.now())
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();

        surveyRepository.save(newSurvey);
    }

    private SurveyQuestion buildQuestion(Integer count, String question) {
        return SurveyQuestion.builder()
                .number(count)
                .question(question)
                .build();
    }
}
