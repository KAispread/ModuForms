package com.modu.ModuForm.app.service.user;

import com.modu.ModuForm.app.domain.surbay.Survey;
import com.modu.ModuForm.app.domain.surbay.SurveyQuestion;
import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    private User userRegist() {
        return User.builder()
                .birth(19980112L)
                .phone(1234L)
                .email("asdfasdf@vccoom")
                .name("기우")
                .role(Role.USER)
                .build();
    }

    private Survey surveyRegist(User user, List<SurveyQuestion> surveyQuestions){
        return Survey.builder()
                .user(user)
                .title("참여조사")
                .description("회식에 참여할지에 대한 여론 조사입니다.")
                .postDate(LocalDateTime.now())
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .surveyQuestionList(surveyQuestions)
                .build();
    }

    @Test
    void login() {
    }

    @Test
    void register() {
    }

    @Test
    void 유저의_서브정보를_가져온다() {
    }
}