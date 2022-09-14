package com.modu.ModuForm.order;

import com.modu.ModuForm.Annotaion.MainDiscountPolicy;
import com.modu.ModuForm.discount.DiscountPolicy;
import com.modu.ModuForm.member.Member;
import com.modu.ModuForm.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderServiceImpl  implements  OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //Test용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
