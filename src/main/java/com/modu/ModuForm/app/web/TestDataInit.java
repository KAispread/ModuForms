package com.modu.ModuForm.app.web;

import com.modu.ModuForm.app.domain.surbay.QuesType;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.surbay.SurveyRepository;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.domain.user.acess.Access;
import com.modu.ModuForm.app.domain.user.acess.AccessRepository;
import com.modu.ModuForm.app.domain.user.common.Gender;
import com.modu.ModuForm.app.domain.user.common.Role;
import com.modu.ModuForm.app.web.dto.survey.SurveySave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final DataService service;

//    @PostConstruct
    public void init() {
        service.init();
    }

    @Component
    @RequiredArgsConstructor
    static class DataService {
        private final UserRepository userRepository;
        private final AccessRepository accessRepository;
        private final SurveyRepository surveyRepository;

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
                    .users(user)
                    .userId("ppap012")
                    .password("love112")
                    .build());

            User user2 = userRepository.save(User.builder()
                    .name("강훈")
                    .nickName("Bom")
                    .birth(19980101L)
                    .gender(Gender.MAN)
                    .email("dfsfs@naver.com")
                    .phone("01023141234")
                    .role(Role.USER)
                    .company("AnyangUniv")
                    .build());
            accessRepository.save(Access.builder()
                    .users(user2)
                    .userId("pppp1234")
                    .password("qwer1234")
                    .build());

            List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
            surveyQuestionList.add(SurveyQuestion.builder()
                    .number(0)
                    .question("가장 좋아하는 노래 이름은 무엇인가요?")
                    .questionType(QuesType.SHORT)
                    .build());
            surveyQuestionList.add(SurveyQuestion.builder()
                    .number(1)
                    .question("가장 좋아하는 가수는 누구인가요?")
                    .questionType(QuesType.SHORT)
                    .build());
            surveyQuestionList.add(SurveyQuestion.builder()
                    .number(2)
                    .question("어떤 음악 장르를 선호하십니까?")
                    .questionType(QuesType.MULTIPLE)
                    .distractor("힙합|발라드|R&B|EDM|")
                    .build());
            surveyQuestionList.add(SurveyQuestion.builder()
                    .number(3)
                    .question("요즘 가장 인기있는 아이돌은 누구라고 생각하시나요?")
                    .questionType(QuesType.SHORT)
                    .build());
            SurveySave saveDto = SurveySave.builder()
                    .title("음악 취향 조사")
                    .email("Esdf@mane.com")
                    .description("음악 취향 조사를 위한 설문입니다.")
                    .deadLine("2024-10-06-15-30")
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
            SurveySave saveDto2 = SurveySave.builder()
                    .title("만족도 조사")
                    .email("Esdf@mane.com")
                    .description("본 어플리케이션 만족도 조사입니다.")
                    .deadLine("2024-10-06-15-30")
                    .maximumAnswer(200)
                    .surveyQuestionList(surveyQuestionList2)
                    .build();
            surveyRepository.save(saveDto2.toSurveyEntity(user2));


            for (int i = 0; i <= 100; i++) {
                List<SurveyQuestion> surveyQuestionLists = new ArrayList<>();
                surveyQuestionLists.add(SurveyQuestion.builder()
                        .number(0)
                        .question("회식에 참여하십니까?")
                        .questionType(QuesType.SHORT)
                        .build());
                surveyQuestionLists.add(SurveyQuestion.builder()
                        .number(1)
                        .question("좋아하는 메뉴를 골라주세요")
                        .questionType(QuesType.MULTIPLE)
                        .distractor("고기|회|곱창|족발|치킨")
                        .build());

                SurveySave saveDtos = SurveySave.builder()
                        .title("회식 참여 조사" + i)
                        .description("회식 참여 조사를 위한 설문입니다." + i)
                        .deadLine("2024-10-06-15-30")
                        .maximumAnswer(2000 + i)
                        .surveyQuestionList(surveyQuestionLists)
                        .build();

                surveyRepository.save(saveDtos.toSurveyEntity(user));
            }
        }
    }
}
