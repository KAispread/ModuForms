package com.modu.ModuForm.app.web;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.surbay.answer.AnswerRepository;
import com.modu.ModuForm.app.domain.user.*;
import com.modu.ModuForm.app.web.dto.answer.AnswerSaveDto;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final UserRepository userRepository;
    private final AccessRepository accessRepository;
    private final SurveyRepository surveyRepository;
    private final AnswerRepository answerRepository;

    // 샘플 데이터 생성
    @Transactional
    public void init() {
        User user = userRepository.save(User.builder()
                .name("기우")
                .nickName("Kai")
                .birth(19980101L)
                .gender(Gender.MAN)
                .email("asdf1234@naver.com")
                .phone("01055501341")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());
        accessRepository.save(Access.builder()
                .user(user)
                .userId("ppap012")
                .password("love112")
                .build());

        User user2 = userRepository.save(User.builder()
                .name("예진")
                .nickName("Rachel")
                .birth(19940101L)
                .gender(Gender.WOMAN)
                .email("eoo123@naver.com")
                .phone("01763501341")
                .role(Role.USER)
                .company("AnyangUniv")
                .build());
        accessRepository.save(Access.builder()
                .user(user2)
                .userId("pppp1234")
                .password("qwe123")
                .build());

        List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
        surveyQuestionList.add(SurveyQuestion.builder()
                .number(0)
                .question("회식에 참여하십니까?")
                .questionType(QuesType.SHORT)
                .build());
        surveyQuestionList.add(SurveyQuestion.builder()
                .number(1)
                .question("어떤 음식을 선호하십니까?")
                .questionType(QuesType.MULTIPLE)
                .distractor("회|곱창|고기|치킨|육회")
                .build());

        SurveySaveDto saveDto = SurveySaveDto.builder()
                .title("회식 참여 조사")
                .email("Esdf@mane.com")
                .description("회식 참여 조사를 위한 설문입니다.")
                .deadLine("2022-10-06-15-30")
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestionList)
                .build();
        surveyRepository.save(saveDto.toSurveyEntity(user));

        List<SurveyQuestion> surveyQuestionList2 = new ArrayList<>();
        surveyQuestionList2.add(SurveyQuestion.builder()
                .number(0)
                .question("본 애플리케이션에 만족하십니까?")
                .questionType(QuesType.SHORT)
                .build());
        surveyQuestionList2.add(SurveyQuestion.builder()
                .number(1)
                .question("보완해야될 점은 무엇입니까?")
                .questionType(QuesType.MULTIPLE)
                .distractor("인터페이스|기능|데이터")
                .build());
        surveyQuestionList2.add(SurveyQuestion.builder()
                .number(2)
                .question("의견이 있다면 남겨주세요.")
                .questionType(QuesType.SHORT)
                .build());

        SurveySaveDto saveDto2 = SurveySaveDto.builder()
                .title("만족도 조사")
                .email("Esdf@mane.com")
                .description("회식 참여 조사를 위한 설문입니다.")
                .deadLine("2022-10-06-15-30")
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestionList2)
                .build();

        Survey survey1 = surveyRepository.save(saveDto2.toSurveyEntity(user2));

        List<String> answerList1 = new ArrayList<>();
        answerList1.add("예");
        answerList1.add("인터페이스");
        answerList1.add("UI를 좀 더 둥글둥글하게 하면 더 좋을 것 같아요");

        AnswerSaveDto answerSaveDto = AnswerSaveDto.builder()
                .anonymousFlag(true)
                .answerList(answerList1)
                .build();

        answerRepository.save(answerSaveDto.toEntity(survey1, user));
    }
}
