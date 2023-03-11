package com.modu.ModuForm.app;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.user.*;
import com.modu.ModuForm.app.domain.user.acess.Access;
import com.modu.ModuForm.app.domain.user.acess.AccessRepository;
import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.domain.user.common.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyDataInit {
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;
    private final SurveyRepository surveyRepository;

    @Autowired
    public DummyDataInit(UserRepository userRepository, AccessRepository accessRepository, SurveyRepository surveyRepository) {
        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
        this.surveyRepository = surveyRepository;
    }

    private final String USER_ID = "mine1234";
    private final String PASSWORD = "qwer1234";
    private final String NAME = "강훈";
    private final String NICKNAME = "Tom";
    private final String SURVEY_TITLE = "테스트 설문";
    private User user;

    public String getUSER_ID() {
        return USER_ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getSURVEY_TITLE() {
        return SURVEY_TITLE;
    }

    public String getNAME() {
        return NAME;
    }

    public String getNICKNAME() {
        return NICKNAME;
    }

    public User userInit() {
        user = userRepository.save(User.builder()
                .name(NAME)
                .nickName(NICKNAME)
                .birth(19940101L)
                .gender(Gender.MAN)
                .email("asdf1234@naver.com")
                .phone("01093129311")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());
        return user;
    }

    public Access accessInit() {
        Access access = Access.builder()
                .users(user)
                .userId(USER_ID)
                .password(PASSWORD)
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

    public SurveyQuestion buildQuestion(Integer count, String question, String distractor, QuesType quesType) {
        return SurveyQuestion.builder()
                .number(count)
                .questionType(quesType)
                .question(question)
                .distractor(distractor)
                .build();
    }

    public Survey surveyInit() {
        Survey newSurvey = Survey.builder()
                .users(user)
                .title(SURVEY_TITLE)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestionInit())
                .build();

        newSurvey.setUsers(user);
        surveyRepository.save(newSurvey);
        return newSurvey;
    }

    public Survey surveyInit(User targetUser) {
        Survey newSurvey = Survey.builder()
                .users(targetUser)
                .title(SURVEY_TITLE)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestionInit())
                .build();

        newSurvey.setUsers(user);
        surveyRepository.save(newSurvey);
        return newSurvey;
    }

    public Survey surveyInit(List<SurveyQuestion> surveyQuestionList) {
        Survey newSurvey = Survey.builder()
                .users(user)
                .title(SURVEY_TITLE)
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestionList)
                .build();

        newSurvey.setUsers(user);
        surveyRepository.save(newSurvey);
        return newSurvey;
    }
}
