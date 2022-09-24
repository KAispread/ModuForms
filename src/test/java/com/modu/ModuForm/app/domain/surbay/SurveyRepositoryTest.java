package com.modu.ModuForm.app.domain.surbay;

import com.modu.ModuForm.app.domain.user.Role;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.domain.user.UserRepository;
import com.modu.ModuForm.app.domain.user.admin.Admin;
import com.modu.ModuForm.app.domain.user.admin.AdminRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveyRepositoryTest {
    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    AdminRepository adminRepository;

//    @AfterEach
//    public void cleanUp(){
//        surveyRepository.deleteAll();
//    }

    @Test
    @DisplayName("설문이_등록된다.")
    public void surveyRegistration() {
        //given

        Admin admin = Admin.builder()
                        .user(null)
                        .name("KAI")
                        .phone("2323")
                        .company("Inflearn")
                        .email("asdfa@com")
                        .build();
        adminRepository.save(admin);

        Survey newSurvey = Survey.builder()
                .admin(admin)
                .postDate(LocalDateTime.now())
                .deadLine(LocalDateTime.of(2022, 9, 30, 20, 0))
                .maximumAnswer(200)
                .build();

        surveyRepository.save(newSurvey);

        //when
        Optional<Survey> survey = surveyRepository.findById(newSurvey.getId());

        //then;
        assertThat(survey.orElseThrow().getMaximumAnswer()).isEqualTo(200);
        assertThat(survey.orElseThrow().getMaximumAnswer()).isEqualTo(LocalDateTime.of(2022,9,30,20,0));
    }
}