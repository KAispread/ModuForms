package com.modu.ModuForm.member;

public interface MemberService {
    void joinMember(Member member);
    Member findMember(Long memberId);
}
