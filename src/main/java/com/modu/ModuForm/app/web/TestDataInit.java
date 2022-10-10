package com.modu.ModuForm.app.web;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.user.*;
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

    // 샘플 데이터 생성
    @PostConstruct
    @Transactional
    public void init() {
        User user = userRepository.save(User.builder()
                .name("기우")
                .nickName("Kai")
                .birth(19980101L)
                .gender(Gender.MAN)
                .email("asdf1234@naver.com")
                .phone(17635081L)
                .role(Role.USER)
                .company("AnyangUniv")
                .build());
        accessRepository.save(Access.builder()
                .user(user)
                .userId("ppap012")
                .password("love112")
                .build());

        List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
        surveyQuestionList.add(SurveyQuestion.builder()
                .number(0)
                .question("회식에 참여하십니까?")
                .questionType(QuesType.SHORT)
                .build());
        surveyQuestionList.add(SurveyQuestion.builder()
                .number(1)
                .question("참여자의 성함을 입력해주세요")
                .questionType(QuesType.SHORT)
                .build());

        SurveySaveDto saveDto = SurveySaveDto.builder()
                .title("회식 참여 조사")
                .description("회식 참여 조사를 위한 설문입니다.")
                .deadLine("2022-10-06-15-30")
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestionList)
                .build();

        surveyRepository.save(saveDto.toSurveyEntity(user));
    }
}
