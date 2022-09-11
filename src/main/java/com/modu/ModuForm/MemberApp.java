package com.modu.ModuForm;

import com.modu.ModuForm.member.Grade;
import com.modu.ModuForm.member.Member;
import com.modu.ModuForm.member.MemberService;
import com.modu.ModuForm.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.joinMember(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
    // psvm = public void static main
    // Ctrl + Alt + V = 변수 자동 생성
}
