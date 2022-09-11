package com.modu.ModuForm.discount;

import com.modu.ModuForm.member.Member;

public interface DiscountPolicy {
    /*
    * @return 할인 대상 금액
    * */
    int discount(Member member, int price);
}
