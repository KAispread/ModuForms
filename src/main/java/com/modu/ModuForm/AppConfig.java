package com.modu.ModuForm;

import com.modu.ModuForm.discount.DiscountPolicy;
import com.modu.ModuForm.discount.FixDiscountPolicy;
import com.modu.ModuForm.discount.RateDiscountPolicy;
import com.modu.ModuForm.member.MemberRepository;
import com.modu.ModuForm.member.MemberService;
import com.modu.ModuForm.member.MemberServiceImpl;
import com.modu.ModuForm.member.MemoryMemberRepository;
import com.modu.ModuForm.order.OrderService;
import com.modu.ModuForm.order.OrderServiceImpl;

public class AppConfig {
    private static MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }
    private static DiscountPolicy getDiscountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    // 구체 클래스를 따로 메소드로 정의하여 반환하게함.

    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }
    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
    }
}
