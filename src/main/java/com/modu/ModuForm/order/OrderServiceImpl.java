package com.modu.ModuForm.order;

import com.modu.ModuForm.discount.DiscountPolicy;
import com.modu.ModuForm.discount.FixDiscountPolicy;
import com.modu.ModuForm.discount.RateDiscountPolicy;
import com.modu.ModuForm.member.Member;
import com.modu.ModuForm.member.MemberRepository;
import com.modu.ModuForm.member.MemoryMemberRepository;

public class OrderServiceImpl  implements  OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
