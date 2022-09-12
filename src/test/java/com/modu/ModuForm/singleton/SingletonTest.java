package com.modu.ModuForm.singleton;

import com.modu.ModuForm.AppConfig;
import com.modu.ModuForm.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        assertThat(singletonService1).isSameAs(singletonService2);
        // same ==
        // equals, Object의 Equals
    }

    @Test
    @DisplayName("스프링 빈에 등록된 객체는 싱글톤으로 관리된다")
    void springContainer() {
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);

        //memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
