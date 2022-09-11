package com.modu.ModuForm;

import com.modu.ModuForm.member.Grade;
import com.modu.ModuForm.member.Member;
import com.modu.ModuForm.member.MemberService;
import com.modu.ModuForm.member.MemberServiceImpl;
import com.modu.ModuForm.order.Order;
import com.modu.ModuForm.order.OrderService;
import com.modu.ModuForm.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.joinMember(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order);
        System.out.println("order.calcuatePrice = " + order.calculatePrice());
    }
}
