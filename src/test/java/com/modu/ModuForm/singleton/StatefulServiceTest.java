package com.modu.ModuForm.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("상태를 가지는 클래스를 Bean 으로 등록하면 안된다")
    void statefulServiceSingleont() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB : B 사용자가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA : A 사용자 주문 금액 조회
        assertThat(userAPrice).isNotSameAs(userBPrice);
    }

    @Configuration
    static class TestConfig{
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}